package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.CompanyCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyCountryRepo extends JpaRepository<CompanyCountry, Long> {

    CompanyCountry findCompanyCountryById(Long id);

    CompanyCountry findCompanyCountryByTitleContainingIgnoreCase(String title);

}
