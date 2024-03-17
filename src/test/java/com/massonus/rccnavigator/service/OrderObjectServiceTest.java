package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.OrderObject;
import com.massonus.rccnavigator.repo.OrderObjectRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderObjectServiceTest {

    @Mock
    private OrderObjectRepo objectRepo;

    @InjectMocks
    private OrderObjectService target;


    @Test
    void saveOrderObject() {
        OrderObject orderObject = new OrderObject();
        orderObject.setTitle("title");
        target.saveOrderObject(orderObject);

        ArgumentCaptor<OrderObject> objectCaptor = ArgumentCaptor.forClass(OrderObject.class);
        verify(objectRepo, times(1)).save(objectCaptor.capture());

        OrderObject savedObject = objectCaptor.getValue();
        assertEquals(savedObject.getTitle(), orderObject.getTitle());
    }
}