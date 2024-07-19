package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);

    Set<Comment> findCommentsByItemId(Long itemId);

}
