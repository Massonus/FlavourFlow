package com.massonus.flavourflow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {

    private Long companyId;

    private String title;

    private Long countryId;

    private Long categoryId;

    private String imageLink;

    private String description;
}
