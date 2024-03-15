package com.massonus.rccnavigator.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "wishes")
@NoArgsConstructor
public class Wish {
    private static final String SEQUENCE_NAME = "wish_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wish",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WishObject> wishObjects = new ArrayList<>();

    public Wish(User user, List<WishObject> wishObjects) {
        this.user = user;
        this.wishObjects = wishObjects;
    }
}
