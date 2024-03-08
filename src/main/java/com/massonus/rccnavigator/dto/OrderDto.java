package com.massonus.rccnavigator.dto;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private String name;

    private String phone;

    @Future
    private Date date;

    private Boolean isSuccess;

    private Long companyId;
}
