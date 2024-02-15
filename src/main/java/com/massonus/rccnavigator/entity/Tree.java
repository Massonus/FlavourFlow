package com.massonus.rccnavigator.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name can not be Blank")
    private String name;

    private long age;

    private Boolean isGreen;

    @OneToOne
    private User user;

    public Tree() {
    }
}
