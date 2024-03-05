package com.massonus.rccnavigator.entity;

import jakarta.persistence.*;
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

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    private String imageLink;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    @Column(columnDefinition = "text", name = "price_category")
    @Enumerated(EnumType.STRING)
    private PriceCategory priceCategory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private KitchenCategory kitchenCategory;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CompanyCountry companyCountry;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Rating> rates = new ArrayList<>();

    private Integer rating;

    public Integer getCountOfProducts() {
        return products.size();
    }

    public Integer currentRating() {
        double currentRate = rates.stream()
                .mapToDouble(Rating::getRate)
                .average()
                .orElse(0.0);
        return (int) (Math.round(currentRate));
    }
}
