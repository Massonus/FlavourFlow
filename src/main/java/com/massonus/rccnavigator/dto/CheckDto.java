package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckDto {

    private Long checkId;

    private Integer size;

    private Boolean isSuccess;
}