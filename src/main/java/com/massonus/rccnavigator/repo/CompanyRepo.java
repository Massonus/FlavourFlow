package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Company company SET company.title = :#{#updatedCompany.title}, " +
            "company.companyType = :#{#updatedCompany.companyType}," +
            "company.kitchenType = :#{#updatedCompany.kitchenType}")
    void updateCompany(Company updatedCompany);

    Company findCompanyById(Long id);

    Company findCompanyByTitle(String value);
}
