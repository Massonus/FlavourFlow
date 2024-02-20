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
@Table(name = "company")
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private KitchenCategory category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CompanyCategory type;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<>();

    public Integer getCountOfProducts() {
        return products.size();
    }
}
