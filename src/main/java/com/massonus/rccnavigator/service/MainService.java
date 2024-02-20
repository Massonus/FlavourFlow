package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.entity.PriceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MainService {

    private final CompanyService companyService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyTypeService companyTypeService;

    @Autowired
    public MainService(CompanyService companyService, KitchenCategoryService kitchenCategoryService, CompanyTypeService companyTypeService) {
        this.companyService = companyService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyTypeService = companyTypeService;
    }

    public Set<Company> initialize() {
        KitchenCategory kitchenCategory = new KitchenCategory();
        CompanyType companyType = new CompanyType();
        Set<Company> companies = new HashSet<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 3);
        for (int i = 0; i < lengthMas; i++) {
            kitchenCategory.setTitle("Cat " + lengthMas);
            companyType.setTitle("Comp " + i);
            kitchenCategoryService.saveKitchenCategory(kitchenCategory);
            companyTypeService.saveCompanyType(companyType);
            Company company = companyService.createElementAuto();
            company.setPriceCategory(PriceCategory.MEDIUM);
            company.setKitchenCategory(kitchenCategory);
            company.setCompanyType(companyType);
            companyService.saveCompany(company);
            companies.add(company);
        }
        return companies;
    }
}
