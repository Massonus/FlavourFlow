package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepo extends JpaRepository<CompanyType, Long> {

    CompanyType findCompanyTypeById(Long id);
}
