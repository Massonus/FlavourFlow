package com.massonus.flavourflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
public class Product {
    private static final String SEQUENCE_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Positive
    private Double price;

    private String imageLink;

    @Column(columnDefinition = "text", name = "product_category")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BasketObject> basketObjects = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WishObject> wishObjects = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderObject> orderObjects = new ArrayList<>();

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotBlank(message = "composition cannot be blank")
    private String composition;

    public Boolean getIsDropboxImage() {
        return !Objects.isNull(imageLink) && imageLink.contains("dropbox");
    }

    public Product(Long id, String title, Double price, String imageLink, ProductCategory productCategory, Company company) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageLink = imageLink;
        this.productCategory = productCategory;
        this.company = company;
    }

    public Product(Company company) {
        this.company = company;
    }
}
