package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CompanyFilterDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final ImageService imageService;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, ImageService imageService) {
        this.companyRepo = companyRepo;
        this.imageService = imageService;
    }

    public void saveCompany(final Company validCompany, final Image image, final KitchenCategory category, CompanyCountry type) {
        Company company = new Company();
        company.setTitle(validCompany.getTitle());
        company.setImage(image);
        company.setCompanyCountry(type);
        company.setPriceCategory(validCompany.getPriceCategory());
        company.setProducts(validCompany.getProducts());
        company.setKitchenCategory(category);

        companyRepo.save(company);

    }

    public void editCompany(final Long id, final Company company, KitchenCategory category, CompanyCountry type, MultipartFile multipartFile, String imageLink) {
        Company savedCompany = companyRepo.findCompanyById(id);

        if (!multipartFile.isEmpty()) {
            Image uploadImage = imageService.upload(multipartFile);
            savedCompany.setImage(uploadImage);
        }

        if (!imageLink.isEmpty()) {
            savedCompany.setImageLink(imageLink);
            savedCompany.setImage(null);
        }

        savedCompany.setTitle(company.getTitle());
        savedCompany.setPriceCategory(company.getPriceCategory());
        savedCompany.setCompanyCountry(type);
        savedCompany.setKitchenCategory(category);
    }

    public Page<Company> getCompaniesInPage(CompanyFilterDto companyFilterDto, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        if (Objects.nonNull(companyFilterDto.getCountryId())) {

            return getCompaniesByCountryId(companyFilterDto.getCountryId(), pageable);

        } else if (Objects.nonNull(companyFilterDto.getCategoryId())) {

            return getCompaniesByCategoryId(companyFilterDto.getCategoryId(), pageable);

        } else {
            companyRepo.findAll(pageable);
        }


        return companyRepo.findAll(pageable);
    }

    private Page<Company> getCompaniesByCategoryId(Long categoryId, Pageable pageable) {

        return companyRepo.findCompaniesByKitchenCategoryId(categoryId, pageable);
    }

    private Page<Company> getCompaniesByCountryId(Long countryId, Pageable pageable) {

        return companyRepo.findCompaniesByCompanyCountryId(countryId, pageable);
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

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Set<Company> getAllCompaniesByTitleContainingIgnoreCase(final String title) {
        return companyRepo.findCompaniesByTitleContainingIgnoreCase(title);
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