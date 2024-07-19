package com.massonus.flavourflow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    private Long commentId;

    private Long itemId;

    private String text;

    private Integer likes;

    private Boolean isLiked;
}
