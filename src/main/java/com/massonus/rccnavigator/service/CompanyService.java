package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CheckDto;
import com.massonus.rccnavigator.dto.CompanyDto;
import com.massonus.rccnavigator.dto.CompanyFilterDto;
import com.massonus.rccnavigator.dto.ItemType;
import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final CompanyCountryService countryService;
    private final KitchenCategoryService categoryService;
    private final ProductRepo productRepo;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, CompanyCountryService countryService, KitchenCategoryService categoryService, ProductRepo productRepo) {
        this.companyRepo = companyRepo;
        this.countryService = countryService;
        this.categoryService = categoryService;
        this.productRepo = productRepo;
    }

    public CompanyDto saveCompany(final CompanyDto companyDto) {
        Company company = new Company();
        company.setTitle(companyDto.getTitle());
        company.setCompanyCountry(countryService.getCountryById(companyDto.getCountryId()));
        company.setKitchenCategory(categoryService.getCategoryById(companyDto.getCategoryId()));
        company.setPriceCategory(companyDto.getPriceCategory());

        if (!companyDto.getImageLink().isEmpty()) {
            company.setImageLink(companyDto.getImageLink());
            company.setImage(null);
        }
        companyRepo.save(company);
        return companyDto;
    }

    public CompanyDto editCompany(final CompanyDto companyDto) {
        Company savedCompany = getCompanyById(companyDto.getCompanyId());

        if (!companyDto.getImageLink().isEmpty()) {
            savedCompany.setImageLink(companyDto.getImageLink());
            savedCompany.setImage(null);
        }

        savedCompany.setTitle(companyDto.getTitle());
        savedCompany.setPriceCategory(companyDto.getPriceCategory());
        savedCompany.setCompanyCountry(countryService.getCountryById(companyDto.getCountryId()));
        savedCompany.setKitchenCategory(categoryService.getCategoryById(companyDto.getCategoryId()));
        companyRepo.save(savedCompany);

        return companyDto;
    }

    public Page<Company> getCompaniesInPage(CompanyFilterDto companyFilterDto, Pageable pageable, String sort, String search) {

        List<Company> companies = getAllCompanies();

        if (Objects.nonNull(companyFilterDto.getCountryId())) {
            companies = filterByCountryId(companies, companyFilterDto);
        }

        if (Objects.nonNull(companyFilterDto.getCategoryId())) {
            companies = filterByCategoryId(companies, companyFilterDto);
        }

        if (Objects.nonNull(sort)) {
            companies = getSortedCompanies(sort, companies);
        }

        if (Objects.nonNull(search) && !search.isEmpty()) {
            companies = getSearchCompanies(search);
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), companies.size());

        return new PageImpl<>(companies.subList(start, end), pageable, companies.size());
    }

    private List<Company> filterByCountryId(final List<Company> companies, final CompanyFilterDto companyFilterDto) {
        return companies.stream()
                .filter(company -> company.getCompanyCountry().getId().equals(companyFilterDto.getCountryId()))
                .toList();
    }

    private List<Company> filterByCategoryId(final List<Company> companies, final CompanyFilterDto companyFilterDto) {
        return companies.stream()
                .filter(company -> company.getKitchenCategory().getId().equals(companyFilterDto.getCategoryId()))
                .toList();
    }

    private List<Company> getSortedCompanies(final String sort, List<Company> companies) {

        companies = switch (sort) {

            case "priceDesc" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getPriceCategory))
                    .toList();

            case "priceAsc" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getPriceCategory).reversed())
                    .toList();

            case "nameA" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getTitle))
                    .toList();

            case "nameZ" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getTitle).reversed())
                    .toList();

            default -> companies;
        };

        return companies;

    }

    private List<Company> getSearchCompanies(final String title) {
        KitchenCategory category = categoryService.getCategoryByTitle(title);
        CompanyCountry country = countryService.getCountryByTitle(title);
        List<Company> companies = getCompaniesByTitleContainingIgnoreCase(title);

        if (Objects.nonNull(category)) {
            return getCompaniesByCategoryId(category.getId());

        } else if (Objects.nonNull(country)) {
            return getCompaniesByCountryId(country.getId());

        } else if (companies.isEmpty()) {
            return productRepo.findProductsByTitleContainingIgnoreCase(title).stream()
                    .map(Product::getCompany)
                    .toList();
        } else {
            return companies;
        }
    }

    public CheckDto moveCompaniesToAnotherCountry(final CheckDto checkDto) {
        List<Company> companiesByCountryId = getCompaniesByCountryId(checkDto.getCheckId());
        for (Company company : companiesByCountryId) {
            company.setCompanyCountry(countryService.getCountryById(checkDto.getNewId()));
        }
        checkDto.setItemType(ItemType.COMPANYCOUNTRY);
        return checkDto;
    }

    public CheckDto moveCompaniesToAnotherCategory(final CheckDto checkDto) {
        List<Company> companiesByCategoryId = getCompaniesByCategoryId(checkDto.getCheckId());
        for (Company company : companiesByCategoryId) {
            company.setKitchenCategory(categoryService.getCategoryById(checkDto.getNewId()));
        }
        checkDto.setItemType(ItemType.KITCHENCATEGORY);
        return checkDto;
    }

    public void setCompanyImage(String title, Image image) {
        getCompanyByTitle(title).setImage(image);
    }

    public void setCompanyImage(Long companyId, Image image) {
        getCompanyById(companyId).setImage(image);
    }

    public List<Company> getCompaniesByCountryId(Long countryId) {
        return companyRepo.findCompaniesByCompanyCountryId(countryId);
    }

    public List<Company> getCompaniesByCategoryId(Long categoryId) {
        return companyRepo.findCompaniesByKitchenCategoryId(categoryId);
    }

    public Long deleteCompany(final Company company) {
        companyRepo.delete(company);
        return company.getId();
    }

    public Company getCompanyById(final Long id) {
        return companyRepo.findCompanyById(id);
    }

    public Company getCompanyByTitle(final String title) {
        return companyRepo.findCompanyByTitle(title);
    }

    public List<Company> getCompaniesByTitleContainingIgnoreCase(String title) {
        return companyRepo.findCompaniesByTitleContainingIgnoreCase(title);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll().stream()
                .sorted(Comparator.comparing(Company::getCurrentRating).thenComparing(Company::getCountOfMessages).reversed())
                .toList();
    }
}