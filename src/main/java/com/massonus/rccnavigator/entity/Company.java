package com.massonus.rccnavigator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "company")
@NoArgsConstructor
public class Company {
    private static final String SEQUENCE_NAME = "company_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    private String imageLink;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    @Column(columnDefinition = "text", name = "price_category")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Price category cannot be empty")
    private PriceCategory priceCategory;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketObject> basketObjects = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishObject> wishObjects = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category cannot be empty")
    private KitchenCategory kitchenCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @NotNull(message = "Country cannot be empty")
    private CompanyCountry companyCountry;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Rating> rates = new ArrayList<>();

    @Positive
    private Integer rating;

    public Integer getCountOfProducts() {
        return products.size();
    }

    public Integer getCountOfRates() {
        return rates.size();
    }

    public Integer getCountOfMessages() {
        return messages.size();
    }

    public Integer getCurrentRating() {
        double currentRate = rates.stream()
                .mapToDouble(Rating::getRate)
                .average()
                .orElse(0.0);
        return (int) (Math.round(currentRate));
    }

    public Company(KitchenCategory kitchenCategory, CompanyCountry companyCountry) {
        this.kitchenCategory = kitchenCategory;
        this.companyCountry = companyCountry;
    }

    public Company(String title) {
        this.title = title;
    }
}
