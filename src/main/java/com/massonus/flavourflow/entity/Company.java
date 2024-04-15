package com.massonus.flavourflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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

    private String imageLink;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

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

    public Double getAverageProductsPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public Boolean getIsDropboxImage() {
        return !Objects.isNull(imageLink) && imageLink.contains("dropbox");
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
