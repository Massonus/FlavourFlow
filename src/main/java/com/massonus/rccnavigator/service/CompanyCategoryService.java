package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.repo.CompanyCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyCategoryService {

    private final CompanyCategoryRepo companyCategoryRepo;

    @Autowired
    public CompanyCategoryService(CompanyCategoryRepo companyCategoryRepo) {
        this.companyCategoryRepo = companyCategoryRepo;
    }

    public void saveCompanyCategory(final CompanyType companyType) {

        companyCategoryRepo.save(companyType);
    }


    public Set<CompanyType> getAllTypes() {

        return new HashSet<>(companyCategoryRepo.findAll());
    }

    public CompanyType getTypeById(Long id) {
        return companyCategoryRepo.findCompanyCategoryById(id);
    }

    public void editType(Long id, String title) {

        getTypeById(id).setTitle(title);
    }

    public void deleteType(Long id) {

        companyCategoryRepo.delete(getTypeById(id));
    }
}
