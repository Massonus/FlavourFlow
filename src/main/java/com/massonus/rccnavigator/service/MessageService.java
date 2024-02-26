package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Company;
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
    private final CompanyService companyService;

    @Autowired
    public MessageService(MessageRepo messageRepo, ProductService productService, CompanyService companyService) {
        this.messageRepo = messageRepo;
        this.productService = productService;
        this.companyService = companyService;
    }

    public void saveProductMessage(User user, Long id, String commentText) {
        Product product = productService.getProductById(id);

        Message message = new Message();
        message.setAuthor(user);
        message.setProduct(product);
        message.setText(commentText);
        message.setLikes(new HashSet<>());

        messageRepo.save(message);
    }

    public void saveCompanyMessage(User user, Long id, String commentText) {
        Company company = companyService.getCompanyById(id);

        Message message = new Message();
        message.setAuthor(user);
        message.setCompany(company);
        message.setText(commentText);
        message.setLikes(new HashSet<>());

        messageRepo.save(message);
    }

    public Message getMessageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    public Set<Message> getMessagesByCompanyId(Long id) {
        return messageRepo.findMessagesByCompanyId(id);
    }

    public Set<Message> getMessagesByProductId(Long id) {
        return messageRepo.findMessagesByProductId(id);
    }

    public void deleteMessage(Long messageId) {
        Message messageById = getMessageById(messageId);
        messageRepo.delete(messageById);
    }

    public void editMessage(Long id, String text) {
        Message messageById = getMessageById(id);

        messageById.setText(text);
        messageRepo.save(messageById);

    }

    public void likeMessage(Long id, User user) {
        Message messageById = getMessageById(id);

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
    }

}
