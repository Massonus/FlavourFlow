package com.massonus.flavourflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

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

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Positive
    private Double price;

    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Positive
    private Integer amount;

    public Double getSum() {
        return price * amount;
    }

    public BasketObject() {
        this.amount = 1;
    }

    public BasketObject(Long id, String title, Product product, Double price, Integer amount, Company company) {
        this.id = id;
        this.title = title;
        this.product = product;
        this.price = price;
        this.amount = amount;
        this.company = company;
    }
}
