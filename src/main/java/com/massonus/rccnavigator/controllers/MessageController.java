package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/new-product-message/{id}")
    public String addNewProductMessage(@PathVariable Long id, @AuthenticationPrincipal User user, @RequestParam String comment) {
        messageService.saveMessage(user, id, comment);
        return "redirect:/product/" + id;
    }

    @GetMapping("/delete/{messageId}")
    public String deleteMessage(@PathVariable Long messageId) {

        Long productId = messageService.deleteMessage(messageId);
        return "redirect:/product/" + productId;
    }

    @GetMapping("/like/{id}")
    public String like(@AuthenticationPrincipal User user, @PathVariable Long id) {

        Long productId = messageService.likeMessage(id, user);

        return "redirect:/product/" + productId;
    }

    @GetMapping("/editing/{id}")
    public String editMessage(@PathVariable Long id, Model model) {

        model.addAttribute("message", messageService.getMessageById(id));

        return "message/messageEdit";
    }

    @PostMapping("/edit/{id}")
    public String editMessage(@PathVariable Long id,
                              @RequestParam String text) {

        Long productId = messageService.editMessage(id, text);

        return "redirect:/product/" + productId;
    }


}
