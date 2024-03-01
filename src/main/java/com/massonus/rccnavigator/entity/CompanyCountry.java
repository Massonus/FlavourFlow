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
@Table(name = "company_country")
@NoArgsConstructor
public class CompanyCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "companyCountry",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Company> companies = new HashSet<>();
}
