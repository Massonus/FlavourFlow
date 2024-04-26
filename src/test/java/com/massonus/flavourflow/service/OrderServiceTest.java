package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.OrderDto;
import com.massonus.flavourflow.entity.Order;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepo orderRepo;
    private UserService userService;
    private OrderService target;

    @BeforeEach
    void setUp() {
        orderRepo = mock(OrderRepo.class);
        OrderObjectService orderObjectService = mock(OrderObjectService.class);
        BasketObjectService basketObjectService = mock(BasketObjectService.class);
        BasketService basketService = mock(BasketService.class);
        CompanyService companyService = mock(CompanyService.class);
        userService = mock(UserService.class);
        target = new OrderService(orderRepo, orderObjectService, basketService, basketObjectService, companyService, userService);
    }

    /*@Test
    void shouldCheckout() {
        User user = new User();
        user.setId(1L);

        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(user.getId());
        orderDto.setDate(Date.valueOf(LocalDate.now()));
        orderDto.setTime(LocalTime.now());

        when(userService.getUserById(orderDto.getUserId())).thenReturn(user);

        OrderDto checkout = target.checkout(orderDto);
        assertTrue(checkout.getIsSuccess());

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepo, times(1)).save(orderCaptor.capture());

        Order savedOrder = orderCaptor.getValue();
        assertEquals(savedOrder.getUser().getId(), orderDto.getUserId());
        assertEquals(savedOrder.getDate(), orderDto.getDate());
    }*/
}