package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.CommentDto;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@PreAuthorize("isAuthenticated()")
public class MessageController {

    private final CommentService commentService;

    @Autowired
    public MessageController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    @ResponseBody
    public CommentDto addNewProductMessage(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user) {

        return commentService.saveMessage(commentDto, user);
    }

    @GetMapping("/edit")
    public String getMessageEditForm(Model model, @RequestParam Long commentId, @RequestParam Long itemId) {

        model.addAttribute("comment", commentService.getCommentById(commentId));
        model.addAttribute("itemId", itemId);

        return "comment/editComment";
    }
    @PutMapping("/edit")
    @ResponseBody
    public CommentDto editTheMessage(@RequestBody CommentDto commentDto) {

        return commentService.editMessage(commentDto);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public CommentDto deleteMessage(@RequestBody CommentDto commentDto) {

        commentService.deleteMessage(commentDto.getCommentId());

        return commentDto;

    }

    @PutMapping("/like")
    @ResponseBody
    public CommentDto like(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user) {

        return commentService.likeComment(commentDto, user);
    }
}
