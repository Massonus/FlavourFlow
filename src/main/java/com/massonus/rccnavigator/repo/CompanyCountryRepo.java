package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.CompanyCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCountryRepo extends JpaRepository<CompanyCountry, Long> {

    CompanyCountry findCompanyTypeById(Long id);

    CompanyCountry findCompanyTypeByTitle(String title);
}
