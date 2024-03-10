package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
