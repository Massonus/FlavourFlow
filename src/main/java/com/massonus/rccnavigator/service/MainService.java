package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyCategory;
import com.massonus.rccnavigator.entity.KitchenCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MainService {

    private final CompanyService companyService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyCategoryService companyCategoryService;

    @Autowired
    public MainService(CompanyService companyService, KitchenCategoryService kitchenCategoryService, CompanyCategoryService companyCategoryService) {
        this.companyService = companyService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyCategoryService = companyCategoryService;
    }

    public Set<Company> initialize() {
        KitchenCategory kitchenCategory = new KitchenCategory();
        CompanyCategory companyCategory = new CompanyCategory();
        Set<Company> companies = new HashSet<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 3);
        for (int i = 0; i < lengthMas; i++) {
            kitchenCategory.setTitle("Cat " + lengthMas);
            companyCategory.setTitle("Comp " + i);
            kitchenCategoryService.saveKitchenCategory(kitchenCategory);
            companyCategoryService.saveCompanyCategory(companyCategory);
            Company company = companyService.createElementAuto();
            company.setCategory(kitchenCategory);
            company.setType(companyCategory);
            companyService.saveCompany(company);
            companies.add(company);
        }
        return companies;
    }
}
