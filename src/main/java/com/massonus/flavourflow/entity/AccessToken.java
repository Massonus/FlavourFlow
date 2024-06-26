package com.massonus.flavourflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AccessToken {

    @Id
    private Long id;

    private String value;
}
