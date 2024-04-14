package com.massonus.flavourflow.dto;

import com.massonus.flavourflow.entity.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

    private Long productId;

    private Long companyId;

    private String title;

    private Double price;

    private ProductCategory productCategory;

    private String imageLink;

}
