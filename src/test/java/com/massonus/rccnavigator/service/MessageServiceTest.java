package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.MessageDto;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.MessageRepo;
import com.massonus.rccnavigator.repo.OrderRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MessageServiceTest {

    private CompanyService companyService;
    private MessageRepo messageRepo;
    private MessageService target;

    private User user;
    private MessageDto messageDto;

    @BeforeEach
    void setUp() {
        companyService = mock(CompanyService.class);
        messageRepo = mock(MessageRepo.class);
        target = new MessageService(messageRepo, companyService);

        user = new User();
        messageDto = new MessageDto();
    }

    @Test
    void saveMessage() {
    }

    @Test
    void editMessage() {
    }

    @Test
    void deleteMessage() {
    }

    @Test
    void likeMessage() {
    }
}