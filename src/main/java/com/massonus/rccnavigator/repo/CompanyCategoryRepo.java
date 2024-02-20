package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.CompanyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCategoryRepo extends JpaRepository<CompanyCategory, Long> {

    CompanyCategory findCompanyCategoryById(Long id);
}
