package com.massonus.rccnavigator.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketObjectDto {

    private Long objectId;

    private Long basketId;

    private Long productId;

    @Positive
    private Integer amount;

    private Double sum;

    private Double total;

    private Long userId;
}
