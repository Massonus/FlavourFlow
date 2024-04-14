package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.CompanyCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCountryRepo extends JpaRepository<CompanyCountry, Long> {

    CompanyCountry findCompanyCountryById(Long id);

    CompanyCountry findCompanyCountryByTitleContainingIgnoreCase(String title);

}
