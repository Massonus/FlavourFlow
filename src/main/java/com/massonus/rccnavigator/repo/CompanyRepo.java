package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    Company findCompanyByTitle(String title);

    List<Company> findCompaniesByTitleContainingIgnoreCase(String title);

    List<Company> findCompaniesByCompanyCountryId(Long countryId);

    List<Company> findCompaniesByKitchenCategoryId(Long categoryId);
}
