package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckDto {

    private Long checkId;

    private Long newId;

    private Integer size;

    private Boolean isSuccess;

    private ItemType itemType;
}
