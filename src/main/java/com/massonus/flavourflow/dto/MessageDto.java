package com.massonus.flavourflow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {

    private Long messageId;

    private Long itemId;

    private String text;

    private Integer likes;

    private Boolean isLiked;
}
