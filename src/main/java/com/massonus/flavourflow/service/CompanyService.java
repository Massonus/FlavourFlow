package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.*;
import com.massonus.flavourflow.entity.Company;
import com.massonus.flavourflow.entity.CompanyCountry;
import com.massonus.flavourflow.entity.KitchenCategory;
import com.massonus.flavourflow.entity.Product;
import com.massonus.flavourflow.repo.CompanyRepo;
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
    private final ProductService productService;
    private final ImageService imageService;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, CompanyCountryService countryService, KitchenCategoryService categoryService, ProductService productService, ImageService imageService) {
        this.companyRepo = companyRepo;
        this.countryService = countryService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.imageService = imageService;
    }

    public CompanyDto saveCompany(final CompanyDto companyDto) {
        Company company = new Company();
        company.setTitle(companyDto.getTitle());
        company.setCompanyCountry(countryService.getCountryById(companyDto.getCountryId()));
        company.setKitchenCategory(categoryService.getCategoryById(companyDto.getCategoryId()));
        company.setDescription(companyDto.getDescription());

        if (!companyDto.getImageLink().isEmpty()) {
            company.setImageLink(companyDto.getImageLink());
        }
        Company save = companyRepo.save(company);
        companyDto.setCompanyId(save.getId());
        return companyDto;
    }

    public CompanyDto editCompany(final CompanyDto companyDto) {
        Company savedCompany = getCompanyById(companyDto.getCompanyId());

        if (savedCompany.getIsDropboxImage()) {
            deleteCompanyImage(savedCompany);
        }

        if (!companyDto.getImageLink().isEmpty()) {
            savedCompany.setImageLink(companyDto.getImageLink());
            deleteCompanyImage(savedCompany);
        }

        savedCompany.setTitle(companyDto.getTitle());
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
                    .sorted(Comparator.comparing(Company::getAverageProductsPrice))
                    .toList();

            case "priceAsc" -> companies.stream()
                    .sorted(Comparator.comparing(Company::getAverageProductsPrice).reversed())
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
            return productService.getProductsByTitleContainingIgnoreCase(title).stream()
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

    public void setCompanyImage(final Long companyId, final ImageResponseDto responseDto) {
        Company companyById = getCompanyById(companyId);
        companyById.setImageLink(responseDto.getUrl());
    }

    public List<Company> getCompaniesByCountryId(Long countryId) {
        return companyRepo.findCompaniesByCompanyCountryId(countryId);
    }

    public List<Company> getCompaniesByCategoryId(Long categoryId) {
        return companyRepo.findCompaniesByKitchenCategoryId(categoryId);
    }

    public ImageResponseDto deleteCompany(final Long companyId) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();

        Company companyById = getCompanyById(companyId);

        if (companyById.getIsDropboxImage()) {
            imageResponseDto = deleteCompanyImage(companyById);
            if (imageResponseDto.getStatus() == 500) {
                return imageResponseDto;
            }
        }

        imageResponseDto.setStatus(200);
        productService.getAllProductsByCompanyId(companyId).forEach(productService::deleteProductImage);

        companyRepo.delete(companyById);
        return imageResponseDto;
    }

    public ImageResponseDto deleteCompanyImage(final Company company) {

        return imageService.deleteImage("company".toUpperCase(), company.getId());
    }

    public Company getCompanyById(final Long id) {
        return companyRepo.findCompanyById(id);
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