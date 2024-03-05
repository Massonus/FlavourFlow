package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CompanyFilterDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public Page<Company> getCompaniesInPage(CompanyFilterDto companyFilterDto, Pageable pageable, String sort) {

        List<Company> companies = getAllCompanies();

        if (Objects.nonNull(companyFilterDto.getCountryId())) {

            companies = companies.stream()
                    .filter(company -> company.getCompanyCountry().getId().equals(companyFilterDto.getCountryId()))
                    .toList();
        }

        if (Objects.nonNull(companyFilterDto.getCategoryId())) {

            companies = companies.stream()
                    .filter(company -> company.getKitchenCategory().getId().equals(companyFilterDto.getCategoryId()))
                    .toList();
        }

        if (Objects.nonNull(sort)) {
            companies = getSortedCompanies(sort, companies);
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), companies.size());

        return new PageImpl<>(companies.subList(start, end), pageable, companies.size());
    }

    public List<Company> getSortedCompanies(String sort, List<Company> companies) {

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
}