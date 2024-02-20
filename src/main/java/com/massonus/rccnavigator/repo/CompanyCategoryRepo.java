package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCategoryRepo extends JpaRepository<CompanyType, Long> {

    CompanyType findCompanyCategoryById(Long id);
}
