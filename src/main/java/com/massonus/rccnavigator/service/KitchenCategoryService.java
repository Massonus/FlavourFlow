package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.KitchenCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class KitchenCategoryService {

    private final KitchenCategoryRepo kitchenCategoryRepo;
    private final CompanyService companyService;

    @Autowired
    public KitchenCategoryService(KitchenCategoryRepo kitchenCategoryRepo, CompanyService companyService) {
        this.kitchenCategoryRepo = kitchenCategoryRepo;
        this.companyService = companyService;
    }

    public Set<Company> createAndFillCompanyListForCategory() {
        KitchenCategory kitchenCategory = new KitchenCategory();
        Set<Company> companies = new HashSet<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 3);
        for (int i = 0; i < lengthMas; i++) {
            kitchenCategory.setTitle("Cat " + lengthMas);
            kitchenCategoryRepo.save(kitchenCategory);
            Company company = companyService.createElementAuto();
            company.setCategory(kitchenCategory);
            companyService.saveCompany(company);
            companies.add(company);
        }
        return companies;
    }

    public void saveCategory(final KitchenCategory kitchenCategory) {

        kitchenCategoryRepo.save(kitchenCategory);
    }

    public Set<KitchenCategory> getAllCategories() {

        return new HashSet<>(kitchenCategoryRepo.findAll());
    }

    public KitchenCategory getCategoryById(Long id) {
        return kitchenCategoryRepo.findKitchenCategoryById(id);
    }

    public void editCategory(Long id, String title) {

        kitchenCategoryRepo.findKitchenCategoryById(id).setTitle(title);

    }

    public void deleteCategory(Long id) {

        kitchenCategoryRepo.delete(getCategoryById(id));
    }

}
