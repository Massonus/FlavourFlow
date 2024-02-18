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

    @GetMapping("/delete/{messageId}/{productId}")
    public String deleteMessage(@PathVariable Long productId, @PathVariable Long messageId, @AuthenticationPrincipal User user) {

        messageService.deleteMessage(productId, messageId);
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

    @GetMapping("/messages/{message}/like")
    public String like(@AuthenticationPrincipal User currentUser,
            @PathVariable Message message) {
        Set<User> likes = message.getLikes();

        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }*/


}
