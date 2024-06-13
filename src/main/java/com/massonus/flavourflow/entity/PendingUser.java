package com.massonus.flavourflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pending_users")
public class PendingUser {

    @Id
    private Long id;

    private Integer telegramId;
}
