package com.massonus.flavourflow.dto;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    @Future
    private Date date;

    private LocalTime time;

    private Integer countGuests;

    private Boolean isSuccess;

    private Boolean isTimeError;

    private Long companyId;

    private Long userId;
}
