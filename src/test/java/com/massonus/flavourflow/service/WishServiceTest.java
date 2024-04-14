package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.ItemDto;
import com.massonus.flavourflow.entity.*;
import com.massonus.flavourflow.repo.WishObjectRepo;
import com.massonus.flavourflow.repo.WishRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishServiceTest {

    private WishService target;
    private WishRepo wishRepo;
    private UserService userService;
    private WishObjectRepo wishObjectRepo;

    private User user;
    private Product product;
    private Wish expectedWish;
    private WishObject wishObject;

    @BeforeEach
    void setUp() {
        wishRepo = mock(WishRepo.class);
        userService = mock(UserService.class);
        wishObjectRepo = mock(WishObjectRepo.class);
        WishObjectService wishObjectService = new WishObjectService(wishObjectRepo);
        target = new WishService(wishRepo, userService, wishObjectService);

        product = new Product(1L, "test", 12.3, "/test", ProductCategory.MEAL, new Company());
        wishObject = new WishObject(1L,"first", product);
        expectedWish = new Wish();
        user = new User();
        user.setId(1L);
    }

    @Test
    void shouldAddProductToWishes() {

        when(wishRepo.findWishByUserId(user.getId())).thenReturn(expectedWish);
        target.addProductToWishes(product, user);

        WishObject first = expectedWish.getWishObjects().stream()
                .filter(w -> w.getTitle().equals(product.getTitle()))
                .toList().getFirst();

        assertEquals("test", first.getTitle());
        verify(wishRepo, times(1)).save(expectedWish);
    }

    @Test
    void shouldGetUserWishWhenWishDoesNotExist() {
        when(userService.getUserById(user.getId())).thenReturn(user);

        Wish savedWish = target.getUserWish(user.getId());
        assertNotSame(expectedWish, savedWish);
    }

    @Test
    void shouldGetUserWishWhenWishExist() {
        when(wishRepo.findWishByUserId(user.getId())).thenReturn(expectedWish);

        Wish savedWish = target.getUserWish(user.getId());
        assertSame(expectedWish, savedWish);
    }

    @Test
    void shouldBeInWishes() {
        Wish wish = new Wish(user, List.of(wishObject));

        when(wishRepo.findWishByUserId(user.getId())).thenReturn(wish);

        Boolean inWishes = target.isInWishes(product.getId().toString(), user.getId().toString());
        assertTrue(inWishes);

    }

    @Test
    void shouldReturnIdOfDeletedWishObject() {
        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(product.getId());
        when(wishObjectRepo.findWishObjectByProductIdAndUserId(product.getId(), user.getId())).thenReturn(wishObject);

        ItemDto returnedDto = target.deleteWishItem(itemDto, user);
        assertSame(returnedDto.getItemId(), wishObject.getId());

    }

    @Test
    void shouldClearWishes() {
        Boolean isEmpty = target.clearWishes(user);
        assertTrue(isEmpty);
    }
}