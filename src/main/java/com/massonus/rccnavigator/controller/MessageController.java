package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.MessageDto;
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

    @PostMapping("/add")
    @ResponseBody
    public MessageDto addNewProductMessage(@RequestBody MessageDto messageDto, @AuthenticationPrincipal User user) {

        return messageService.saveMessage(messageDto, user);
    }

    @GetMapping("/edit")
    public String getMessageEditForm(Model model, @RequestParam Long messageId, @RequestParam Long itemId) {

        model.addAttribute("message", messageService.getMessageById(messageId));
        model.addAttribute("itemId", itemId);

        return "message/editMessage";
    }
    @PutMapping("/edit")
    @ResponseBody
    public MessageDto editTheMessage(@RequestBody MessageDto messageDto) {

        return messageService.editMessage(messageDto);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public MessageDto deleteMessage(@RequestBody MessageDto messageDto) {

        messageService.deleteMessage(messageDto.getMessageId());

        return messageDto;

    }

    @PutMapping("/like")
    @ResponseBody
    public MessageDto like(@RequestBody MessageDto messageDto, @AuthenticationPrincipal User user) {

        messageService.likeMessage(messageDto.getMessageId(), user);
        return messageDto;
    }
}
