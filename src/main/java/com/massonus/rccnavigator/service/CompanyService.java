package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    private final ProductService productService;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, ProductService productService) {
        this.companyRepo = companyRepo;
        this.productService = productService;
    }

    public Company saveCompany(final Company validCompany, final Image image, final KitchenCategory category, CompanyType type) {
        Company company = new Company();
        company.setTitle(validCompany.getTitle());
        company.setImage(image);
        company.setCompanyType(type);
        company.setPriceCategory(validCompany.getPriceCategory());
        company.setProducts(validCompany.getProducts());
        company.setKitchenCategory(category);

        companyRepo.save(company);

        return company;
    }

    public void editCompany(final Long id, final Company company, KitchenCategory category, CompanyType type) {
        Company savedCompany = companyRepo.findCompanyById(id);

        savedCompany.setTitle(company.getTitle());
        savedCompany.setPriceCategory(company.getPriceCategory());
        savedCompany.setCompanyType(type);
        savedCompany.setKitchenCategory(category);
    }

    public void saveCompany(Company company) {
        companyRepo.save(company);
    }

    public void deleteCompany(final Company company) {
        companyRepo.delete(company);
    }

    public Company getCompanyById(final Long id) {
        return companyRepo.findCompanyById(id);
    }

    public Company getCompanyByTitle(final String title) {
        return companyRepo.findCompanyByTitle(title);
    }

    public Set<Company> getCompaniesByCategoryId(Long categoryId) {
        return companyRepo.findCompaniesByKitchenCategoryId(categoryId);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Set<Company> getAllCompaniesByTitleContainingIgnoreCase(final String title) {
        return companyRepo.findCompaniesByTitleContainingIgnoreCase(title);
    }

    public Company createElementAuto() {
        Company company = new Company();

        companyRepo.save(company);
        company.setProducts(createAndFillProductsListForCompany(company));

        return company;

    }

    public Set<Product> createAndFillProductsListForCompany(final Company company) {
        Set<Product> products = new HashSet<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 3);
        for (int i = 0; i < lengthMas; i++) {
            Product product = new Product("Title " + i, 21 + i + "", company);
            productService.saveProduct(product);
            products.add(product);
        }
        return products;
    }

    public List<Company> getSortedCompanies(String sort) {

        List<Company> companies = getAllCompanies();

        companies = switch (sort) {

            case "descending" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getPriceCategory))
                    .toList();

            case "ascending" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getPriceCategory).reversed())
                    .toList();

            case "a-z" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getTitle))
                    .toList();

            case "z-a" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getTitle).reversed())
                    .toList();

            default -> companies;
        };

        return companies;

    }
}