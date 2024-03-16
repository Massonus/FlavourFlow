package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.MessageDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepo messageRepo;
    @InjectMocks
    private MessageService target;

    private User user;
    private MessageDto messageDto;

    @BeforeEach
    void setUp() {

        user = new User();
        messageDto = new MessageDto();
        messageDto.setText("text");
        messageDto.setItemId(1L);
    }

    @Test
    void saveMessage() {

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