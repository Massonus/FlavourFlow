package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.WishObject;
import com.massonus.rccnavigator.repo.WishObjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishObjectServiceTest {

    @InjectMocks
    private WishObjectService target;

    @Mock
    private WishObjectRepo wishObjectRepo;

    private WishObject expectedObject;

    @BeforeEach
    void setUp() {
        expectedObject = new WishObject();
    }

    @Test
    void shouldGetWishObjectByProductIdAndUserId() {
        Long productId = 1L;
        Long userId = 1L;

        when(wishObjectRepo.findWishObjectByProductIdAndUserId(productId, userId)).thenReturn(expectedObject);
        WishObject wishObject = target.getWishObjectByProductIdAndUserId(productId, userId);

        assertSame(wishObject, expectedObject);
    }

    @Test
    void shouldGetWishObjectsByUserId() {
        Long userId = 1L;
        List<WishObject> expectedObjects = List.of(new WishObject());
        when(wishObjectRepo.findWishObjectsByUserId(userId)).thenReturn(expectedObjects);

        List<WishObject> wishObjects = target.getWishObjectsByUserId(userId);

        assertSame(expectedObjects, wishObjects);
    }
}