package com.massonus.flavourflow.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long itemId;

    private Long basketId;

    private Long productId;

    @Positive
    private Integer amount;

    private Double sum;

    private Double total;

    private Long userId;

    private Boolean isInBasket;
}
