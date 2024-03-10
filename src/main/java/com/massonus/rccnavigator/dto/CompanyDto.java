package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {

    private Long companyId;

    private String title;

    private String priceCategory;

    private Long countryId;

    private Long categoryId;

    private String imageLink;
}
