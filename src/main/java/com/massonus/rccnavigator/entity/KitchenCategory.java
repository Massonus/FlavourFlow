package com.massonus.rccnavigator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "kitchen_categories")
@NoArgsConstructor
public class KitchenCategory {
    private static final String SEQUENCE_NAME = "category_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "kitchenCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Company> companies = new HashSet<>();

}
