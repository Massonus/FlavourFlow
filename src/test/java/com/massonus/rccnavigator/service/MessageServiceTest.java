package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.MessageDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.MessageRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private CompanyService companyService;
    @Mock
    private MessageRepo messageRepo;
    @InjectMocks
    private MessageService target;

    private User user;
    private MessageDto messageDto;
    private Company company;
    private Message message;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        messageDto = new MessageDto();
        messageDto.setMessageId(1L);
        messageDto.setText("text");
        messageDto.setItemId(1L);

        company = new Company();
        company.setId(1L);
        Set<Message> messages = new HashSet<>();
        message = new Message();
        message.setId(1L);
        message.setItemId(company.getId());
        messages.add(message);
        company.setMessages(messages);

    }

    @Test
    void shouldSaveMessage() {
        when(companyService.getCompanyById(company.getId())).thenReturn(company);

        target.saveMessage(messageDto, user);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepo, times(1)).save(messageCaptor.capture());

        Message savedMessage = messageCaptor.getValue();
        assertEquals(savedMessage.getText(), messageDto.getText());
    }

    @Test
    void shouldEditMessage() {
        when(messageRepo.findMessageById(messageDto.getMessageId())).thenReturn(message);
        target.editMessage(messageDto);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepo, times(1)).save(messageCaptor.capture());

        Message savedMessage = messageCaptor.getValue();
        assertEquals(savedMessage.getText(), messageDto.getText());

    }

    @Test
    void shouldDeleteMessage() {
        when(companyService.getCompanyById(message.getItemId())).thenReturn(company);
        when(messageRepo.findMessageById(messageDto.getMessageId())).thenReturn(message);

        target.deleteMessage(messageDto.getMessageId());

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepo, times(1)).delete(messageCaptor.capture());

        boolean contains = company.getMessages().contains(message);
        assertFalse(contains);
    }

    @Test
    void shouldLikeMessage() {
        when(messageRepo.findMessageById(messageDto.getMessageId())).thenReturn(message);

        MessageDto responseMessageDto = target.likeMessage(messageDto, user);
        Integer likes = responseMessageDto.getLikes();
        assertEquals(message.getLikes().size(), likes);
        assertTrue(messageDto.getIsLiked());
    }

}