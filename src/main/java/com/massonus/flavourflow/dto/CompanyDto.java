package com.massonus.flavourflow.dto;

import com.massonus.flavourflow.entity.PriceCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {

    private Long companyId;

    private String title;

    private PriceCategory priceCategory;

    private Long countryId;

    private Long categoryId;

    private String imageLink;
}
