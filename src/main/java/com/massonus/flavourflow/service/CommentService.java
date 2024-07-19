package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.CommentDto;
import com.massonus.flavourflow.entity.Comment;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final CompanyService companyService;

    @Autowired
    public CommentService(CommentRepo commentRepo, CompanyService companyService) {
        this.commentRepo = commentRepo;
        this.companyService = companyService;
    }

    public CommentDto saveMessage(final CommentDto commentDto, User user) {
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setText(commentDto.getText());
        comment.setItemId(commentDto.getItemId());
        comment.setLikes(new HashSet<>());

        commentRepo.save(comment);

        companyService.getCompanyById(commentDto.getItemId()).getComment().add(comment);

        return commentDto;
    }

    public CommentDto editMessage(final CommentDto commentDto) {
        Comment commentById = getCommentById(commentDto.getCommentId());

        commentById.setText(commentDto.getText());
        commentRepo.save(commentById);

        return commentDto;

    }

    public void deleteMessage(Long messageId) {
        Comment commentById = getCommentById(messageId);
        companyService.getCompanyById(commentById.getItemId()).getComment().remove(commentById);

        commentRepo.delete(commentById);
    }

    public CommentDto likeComment(CommentDto commentDto, User user) {
        Comment commentById = getCommentById(commentDto.getCommentId());

        Set<User> likes = commentById.getLikes();

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
        commentDto.setLikes(commentById.getLikesCount());
        commentDto.setIsLiked(commentById.getIsUnliked());
        return commentDto;
    }

    public Comment getCommentById(Long id) {
        return commentRepo.findCommentById(id);
    }

    public Set<Comment> getCommentsByItemId(final Long itemId) {
        return commentRepo.findCommentsByItemId(itemId);
    }

}
