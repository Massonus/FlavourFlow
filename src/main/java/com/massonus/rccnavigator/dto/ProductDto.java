package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

    private Long productId;

    private Long companyId;

    private String title;

    private Double price;

    private String productCategory;

    private String imageLink;

}
