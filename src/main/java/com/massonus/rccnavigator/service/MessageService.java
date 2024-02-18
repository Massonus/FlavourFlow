package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        messageRepo.save(message);
    }

    public Message getMessageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    public void deleteMessage(Long productId, Long messageId) {
        List<Message> messages = productService.getProductById(productId).getMessages();
        final Message messageById = getMessageById(messageId);
        messages.remove(messageById);
    }

}
