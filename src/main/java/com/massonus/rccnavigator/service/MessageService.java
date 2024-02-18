package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final ProductService productService;

    @Autowired
    public MessageService(MessageRepo messageRepo, ProductService productService) {
        this.messageRepo = messageRepo;
        this.productService = productService;
    }

    public void saveMessage(User user, Long id, String commentText) {
        Product product = productService.getProductById(id);

        Message message = new Message();
        message.setAuthor(user);
        message.setProduct(product);
        message.setCompany(product.getCompany());
        message.setText(commentText);
        message.setMeLiked(false);
        message.setLikes(new HashSet<>());

        messageRepo.save(message);
    }

    public Message getMessageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    public Long deleteMessage(Long messageId) {
        Message messageById = getMessageById(messageId);
        messageRepo.delete(messageById);
        return messageById.getProduct().getId();
    }

    public Long likeMessage(Long id, User user) {
        Message messageById = getMessageById(id);
        messageById.getLikes().add(user);
        return messageById.getProduct().getId();
    }

}
