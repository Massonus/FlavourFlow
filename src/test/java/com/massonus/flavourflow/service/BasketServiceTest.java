package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.ItemDto;
import com.massonus.flavourflow.entity.*;
import com.massonus.flavourflow.repo.BasketRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {
    @Mock
    private BasketRepo basketRepo;
    @Mock
    private UserService userService;
    @Mock
    private BasketObjectService basketObjectService;
    @InjectMocks
    private BasketService target;

    private User user;
    private Product product;
    private Basket basket;
    private BasketObject basketObject;
    private ItemDto itemDto;
    private List<BasketObject> basketObjects;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "test", 12.3, "/test", ProductCategory.MEAL, new Company());
        basketObject = new BasketObject(1L, "first", product, 13.2, 2, new Company("PUS"));

        basketObjects = new ArrayList<>();
        basketObjects.add(basketObject);
        basketObjects.add(new BasketObject(2L, "second", new Product(), 12.2, 3, new Company("ONS")));

        basket = new Basket(user, basketObjects);

        user = new User();
        user.setId(1L);

        itemDto = new ItemDto();
        itemDto.setProductId(product.getId());
        itemDto.setAmount(2);
        itemDto.setUserId(user.getId());
    }

    @Test
    void shouldAddProductToBasket() {
        when(basketRepo.findBasketByUserId(user.getId())).thenReturn(basket);
        target.addProductToBasket(product, user);

        ArgumentCaptor<Basket> basketCaptor = ArgumentCaptor.forClass(Basket.class);
        verify(basketRepo, times(1)).save(basketCaptor.capture());

        Basket savedCompany = basketCaptor.getValue();
        BasketObject savedObject = savedCompany.getBasketObjects().getFirst();
        assertSame(savedObject.getProduct(), product);
    }

    @Test
    void shouldGetUserBasketWhenBasketDoesNotExist() {
        when(userService.getUserById(user.getId())).thenReturn(user);

        Basket savedBasket = target.getUserBasket(user.getId());
        assertNotSame(basket, savedBasket);
    }

    @Test
    void shouldGetUserBasketWhenBasketExist() {
        when(basketRepo.findBasketByUserId(user.getId())).thenReturn(basket);

        Basket savedBasket = target.getUserBasket(user.getId());
        assertSame(basket, savedBasket);
    }

    @Test
    void shouldBeInBasket() {
        when(basketRepo.findBasketByUserId(user.getId())).thenReturn(basket);

        Boolean inBasket = target.isInBasket(product.getId().toString(), user.getId().toString());
        assertTrue(inBasket);

    }

    @Test
    void shouldReturnIdOfDeletedBasketObject() {
        when(basketObjectService.getBasketObjectByProductIdAndUserId(product.getId(), user.getId())).thenReturn(basketObject);
        when(basketRepo.findBasketByUserId(user.getId())).thenReturn(basket);

        ItemDto returnedDto = target.deleteBasketItem(itemDto, user);
        assertSame(returnedDto.getItemId(), basketObject.getId());

    }

    @Test
    void shouldClearBasket() {
        Boolean isEmpty = target.clearBasket(user);
        assertTrue(isEmpty);
    }

    @Test
    void shouldChangeAmount() {
        when(basketObjectService.getBasketObjectById(itemDto.getProductId())).thenReturn(basketObject);
        when(basketRepo.findBasketByUserId(itemDto.getUserId())).thenReturn(basket);
        ItemDto responseItemDto = target.changeAmount(itemDto);

        assertEquals(responseItemDto.getSum(), basketObject.getPrice() * itemDto.getAmount());
    }

    @Test
    void shouldGetAllCompaniesInUserBasket() {
        when(basketObjectService.getBasketObjectsByUserId(user.getId())).thenReturn(basketObjects);

        List<String> titleList = target.getAllCompaniesInUserBasket(user).stream()
                .map(Company::getTitle)
                .toList();

        List<String> expectedTitleList = basketObjects.stream()
                .map(BasketObject::getCompany)
                .map(Company::getTitle)
                .toList();

        assertEquals(titleList, expectedTitleList);

    }
}