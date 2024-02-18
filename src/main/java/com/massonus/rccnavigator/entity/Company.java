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

    @Column(columnDefinition = "text", name = "company_type")
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Column(columnDefinition = "text", name = "kitchen_type")
    @Enumerated(EnumType.STRING)
    private KitchenType kitchenType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<>();

    public Company(String title, CompanyType companyType, KitchenType kitchenType) {
        this.title = title;
        this.companyType = companyType;
        this.kitchenType = kitchenType;
    }
}