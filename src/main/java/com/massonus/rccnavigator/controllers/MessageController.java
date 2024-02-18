package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Message;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/new-product-message/{id}")
    public String addNewProductMessage(@PathVariable Long id, @AuthenticationPrincipal User user, @RequestBody String text) {
        messageService.saveMessage(user, id, text);
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
