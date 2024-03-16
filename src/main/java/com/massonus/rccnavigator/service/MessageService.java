package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.MessageDto;
import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public MessageDto saveMessage(final MessageDto messageDto, User user) {
        Message message = new Message();
        message.setAuthor(user);
        message.setText(messageDto.getText());
        message.setItemId(messageDto.getItemId());
        message.setLikes(new HashSet<>());

        messageRepo.save(message);

        return messageDto;
    }

    public MessageDto editMessage(final MessageDto messageDto) {
        Message messageById = getMessageById(messageDto.getMessageId());

        messageById.setText(messageDto.getText());
        messageRepo.save(messageById);

        return messageDto;

    }

    public void deleteMessage(Long messageId) {
        Message messageById = getMessageById(messageId);

        messageRepo.delete(messageById);
    }

    public MessageDto likeMessage(MessageDto messageDto, User user) {
        Message messageById = getMessageById(messageDto.getMessageId());

        Set<User> likes = messageById.getLikes();

        boolean contains = likes.stream()
                .map(User::getId)
                .anyMatch(a -> a.equals(user.getId()));

        if (contains) {
            List<User> collect = likes.stream()
                    .filter(a -> a.getId().equals(user.getId()))
                    .toList();

            likes.remove(collect.getFirst());
        } else {
            likes.add(user);
        }
        messageDto.setLikes(messageById.getLikesCount());
        messageDto.setIsLiked(messageById.getIsUnliked());
        return messageDto;
    }

    public Message getMessageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    public Set<Message> getMessagesByItemId(final Long itemId) {
        return messageRepo.findMessagesByItemId(itemId);
    }

}
