package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketObjectServiceTest {

    @InjectMocks
    private BasketObjectService target;

    @Mock
    private BasketObjectRepo basketObjectRepo;

    private BasketObject expectedObject;

    @BeforeEach
    void setUp() {
        expectedObject = new BasketObject();
    }

    @Test
    void shouldGetBasketObjectByProductIdAndUserId() {
        Long productId = 1L;
        Long userId = 1L;

        when(basketObjectRepo.findBasketObjectByProductIdAndUserId(productId, userId)).thenReturn(expectedObject);
        BasketObject basketObject = target.getBasketObjectByProductIdAndUserId(productId, userId);

        assertSame(basketObject, expectedObject);
    }

    @Test
    void shouldGetBasketObjectsByUserId() {
        Long userId = 1L;
        List<BasketObject> expectedObjects = List.of(new BasketObject());
        when(basketObjectRepo.findBasketObjectsByUserId(userId)).thenReturn(expectedObjects);

        List<BasketObject> basketObjects = target.getBasketObjectsByUserId(userId);

        assertSame(expectedObjects, basketObjects);
    }


}