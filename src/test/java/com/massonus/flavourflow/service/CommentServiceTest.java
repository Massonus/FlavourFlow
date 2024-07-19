package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.CommentDto;
import com.massonus.flavourflow.entity.Comment;
import com.massonus.flavourflow.entity.Company;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.CommentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CompanyService companyService;
    @Mock
    private CommentRepo commentRepo;
    @InjectMocks
    private CommentService target;

    private User user;
    private CommentDto commentDto;
    private Company company;
    private Comment comment;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        commentDto = new CommentDto();
        commentDto.setCommentId(1L);
        commentDto.setText("text");
        commentDto.setItemId(1L);

        company = new Company();
        company.setId(1L);
        Set<Comment> comments = new HashSet<>();
        comment = new Comment();
        comment.setId(1L);
        comment.setItemId(company.getId());
        comments.add(comment);
        company.setComment(comments);

    }

    @Test
    void shouldSaveMessage() {
        when(companyService.getCompanyById(company.getId())).thenReturn(company);

        target.saveMessage(commentDto, user);

        ArgumentCaptor<Comment> messageCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepo, times(1)).save(messageCaptor.capture());

        Comment savedComment = messageCaptor.getValue();
        assertEquals(savedComment.getText(), commentDto.getText());
    }

    @Test
    void shouldEditMessage() {
        when(commentRepo.findCommentById(commentDto.getCommentId())).thenReturn(comment);
        target.editMessage(commentDto);

        ArgumentCaptor<Comment> messageCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepo, times(1)).save(messageCaptor.capture());

        Comment savedComment = messageCaptor.getValue();
        assertEquals(savedComment.getText(), commentDto.getText());

    }

    @Test
    void shouldDeleteMessage() {
        when(companyService.getCompanyById(comment.getItemId())).thenReturn(company);
        when(commentRepo.findCommentById(commentDto.getCommentId())).thenReturn(comment);

        target.deleteMessage(commentDto.getCommentId());

        ArgumentCaptor<Comment> messageCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepo, times(1)).delete(messageCaptor.capture());

        boolean contains = company.getComment().contains(comment);
        assertFalse(contains);
    }

    @Test
    void shouldLikeComment() {
        when(commentRepo.findCommentById(commentDto.getCommentId())).thenReturn(comment);

        CommentDto responseCommentDto = target.likeComment(commentDto, user);
        Integer likes = responseCommentDto.getLikes();
        assertEquals(comment.getLikes().size(), likes);
        assertTrue(commentDto.getIsLiked());
    }

}