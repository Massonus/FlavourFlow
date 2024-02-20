package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    Company findCompanyByTitle(String value);

    Set<Company> findCompaniesByTitleContainingIgnoreCase(String title);

    Set<Company> findCompaniesByKitchenCategoryId(Long categoryId);
}
