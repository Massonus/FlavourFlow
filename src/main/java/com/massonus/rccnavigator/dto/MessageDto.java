package com.massonus.rccnavigator.dto;

import com.massonus.rccnavigator.entity.MessageItemType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {

    private Long messageId;

    private Long itemId;

    private String text;

    private MessageItemType itemType;
}
