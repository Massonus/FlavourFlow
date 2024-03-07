package com.massonus.rccnavigator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "basket_object")
public class BasketObject {

    private static final String SEQUENCE_NAME = "basket_object_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    private Long productId;

    private String title;

    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "basketObjects", fetch = FetchType.LAZY)
    private List<Basket> basket = new ArrayList<>();

    private Integer amount;

    private Double sum;

    public BasketObject() {
        this.amount = 1;
    }

    public Double getSum() {
        return price * amount;
    }
}
