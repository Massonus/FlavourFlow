package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CountryDto;
import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.repo.CompanyCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CompanyCountryService {

    private final CompanyCountryRepo countryRepo;

    @Autowired
    public CompanyCountryService(CompanyCountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public CountryDto saveCompanyCountry(final CountryDto countryDto) {
        CompanyCountry companyCountry = new CompanyCountry();
        companyCountry.setTitle(countryDto.getTitle());

        countryRepo.save(companyCountry);
        return countryDto;
    }


    public List<CompanyCountry> getAllCountries() {

        return countryRepo.findAll().stream().sorted(Comparator.comparing(CompanyCountry::getId)).toList();
    }

    public CompanyCountry getCountryById(Long id) {
        return countryRepo.findCompanyCountryById(id);
    }

    public CompanyCountry getCountryByTitle(String title) {
        return countryRepo.findCompanyCountryByTitleContainingIgnoreCase(title);
    }

    public List<CompanyCountry> getAllCountriesExceptOne(final Long countryId) {
        return countryRepo.findAll().stream()
                .filter(a -> !a.getId().equals(countryId)).toList();
    }

    public CountryDto editCountry(final CountryDto countryDto) {

        getCountryById(countryDto.getCountryId()).setTitle(countryDto.getTitle());
        return countryDto;
    }

    public Long deleteCountry(Long id) {

        countryRepo.delete(getCountryById(id));
        return id;
    }
}
