package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.CompanyCategory;
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

    public void saveCompanyCategory(final CompanyCategory companyCategory) {

        companyCategoryRepo.save(companyCategory);
    }


    public Set<CompanyCategory> getAllCategories() {

        return new HashSet<>(companyCategoryRepo.findAll());
    }

    public CompanyCategory getCategoryById(Long id) {
        return companyCategoryRepo.findCompanyCategoryById(id);
    }

    public void editCategory(Long id, String title) {

        getCategoryById(id).setTitle(title);
    }

    public void deleteCategory(Long id) {

        companyCategoryRepo.delete(getCategoryById(id));
    }
}
