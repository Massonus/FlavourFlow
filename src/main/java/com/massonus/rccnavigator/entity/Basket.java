package com.massonus.rccnavigator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "basket")
public class Basket {

    private static final String SEQUENCE_NAME = "basket_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "basket",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BasketObject> basketObjects = new ArrayList<>();

    public Double getTotal() {
        return basketObjects.stream()
                .mapToDouble(BasketObject::getSum)
                .sum();
    }
}
