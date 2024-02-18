package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/new-product-message/{id}")
    public String addNewProductMessage(@PathVariable Long id, @AuthenticationPrincipal User user, @RequestParam String comment) {
        messageService.saveMessage(user, id, comment);
        return "redirect:/product/" + id;
    }

    @GetMapping("/delete/{messageId}")
    public String deleteMessage(@PathVariable Long messageId, @AuthenticationPrincipal User user) {

        Long productId = messageService.deleteMessage(messageId);
        return "redirect:/product/" + productId;
    }

    @GetMapping("/like/{id}")
    public String like(@AuthenticationPrincipal User user, @PathVariable Long id) {

        Long productId = messageService.likeMessage(id, user);

        return "redirect:/product/" + productId;
    }

    /*@PostMapping("/user-messages/{id}")
    public String updateMessage(@AuthenticationPrincipal User currentUser, @PathVariable Long id,
                                @RequestParam Message message,
                                @RequestParam String text) {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);

            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

    */


}
