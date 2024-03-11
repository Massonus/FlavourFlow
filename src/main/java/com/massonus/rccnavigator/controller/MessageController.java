package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.MessageDto;
import com.massonus.rccnavigator.entity.Message;
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

    @GetMapping("/delete/{messageId}/{item}/{itemId}")
    public String deleteMessage(@PathVariable Long messageId, @PathVariable String item, @PathVariable Long itemId) {

        messageService.deleteMessage(messageId);

        if (item.equals("Company")) {
            return "redirect:/companies/info/" + itemId;
        } else {
            return "redirect:/product/" + itemId;
        }
    }

    @GetMapping("/like/{id}/{item}/{itemId}")
    public String like(@AuthenticationPrincipal User user, @PathVariable Long id, @PathVariable String item, @PathVariable Long itemId) {

        messageService.likeMessage(id, user);

        if (item.equals("Company")) {
            return "redirect:/companies/info/" + itemId;
        } else {
            return "redirect:/product/" + itemId;
        }

    }

    @GetMapping("/editing/{id}/{item}/{itemId}")
    public String editMessage(@PathVariable Long id, Model model, @PathVariable String item, @PathVariable Long itemId) {

        model.addAttribute("message", messageService.getMessageById(id));
        model.addAttribute("itemId", itemId);
        model.addAttribute("item", item);

        return "message/messageEdit";
    }

    @PostMapping("/edit/{id}/{item}/{itemId}")
    public String editMessage(@PathVariable Long id,
                              @RequestParam String text, @PathVariable Long itemId, @PathVariable String item) {

        messageService.editMessage(id, text);

        if (item.equals("Company")) {
            return "redirect:/companies/info/" + itemId;
        } else {
            return "redirect:/product/" + itemId;
        }
    }


}
