package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.repo.WishRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishServiceTest {

    @InjectMocks
    private WishService target;

    @Mock
    private WishRepo wishRepo;

    @Test
    void addProductToWishes() {
        User user = new User();
        user.setId(1L);
        Wish wish = new Wish();
        Product product = new Product("test", 12.3, "/test", ProductCategory.MEAL, new Company());

        when(wishRepo.findWishByUserId(user.getId())).thenReturn(wish);
        target.addProductToWishes(product, user);

        WishObject first = wish.getWishObjects().getFirst();

        assertEquals("test", first.getTitle());

    }

    @Test
    void getUserWish() {
    }

    @Test
    void isInWishes() {
    }

    @Test
    void deleteWishItem() {
    }

    @Test
    void clearWishes() {
    }
}