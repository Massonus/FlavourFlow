package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    List<Company> findCompaniesByTitleContainingIgnoreCase(String title);

    List<Company> findCompaniesByCompanyCountryId(Long countryId);

    List<Company> findCompaniesByKitchenCategoryId(Long categoryId);
}
