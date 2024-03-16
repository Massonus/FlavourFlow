package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.MessageDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.MessageRepo;
import com.massonus.rccnavigator.repo.OrderRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        messageDto.setText("text");
        messageDto.setItemId(1L);
    }

    @Test
    void saveMessage() {
        Company company = new Company();
        company.setId(1L);
        Set<Message> messages = new HashSet<>();
        messages.add(new Message());
        company.setMessages(messages);

        when(companyService.getCompanyById(company.getId())).thenReturn(company);

        target.saveMessage(messageDto, user);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepo, times(1)).save(messageCaptor.capture());

        Message savedMessage = messageCaptor.getValue();
        assertEquals(savedMessage.getText(), messageDto.getText());
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