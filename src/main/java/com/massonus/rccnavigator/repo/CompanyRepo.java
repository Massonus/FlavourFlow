package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findCompanyByTitle(String value);
}
