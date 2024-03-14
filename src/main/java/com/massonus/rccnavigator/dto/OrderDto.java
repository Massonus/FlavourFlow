package com.massonus.rccnavigator.dto;

import com.massonus.rccnavigator.entity.User;
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

    private Long companyId;

    private User user;
}
