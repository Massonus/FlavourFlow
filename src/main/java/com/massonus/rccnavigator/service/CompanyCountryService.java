package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CountryDto;
import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.repo.CompanyCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyCountryService {

    private final CompanyCountryRepo countryRepo;

    @Autowired
    public CompanyCountryService(CompanyCountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public void saveCompanyCountry(final CountryDto countryDto) {
        CompanyCountry companyCountry = new CompanyCountry();
        companyCountry.setTitle(countryDto.getTitle());

        countryRepo.save(companyCountry);
    }


    public Set<CompanyCountry> getAllCountries() {

        return new HashSet<>(countryRepo.findAll());
    }

    public CompanyCountry getCountryById(Long id) {
        return countryRepo.findCompanyTypeById(id);
    }

    public CompanyCountry getCountryByTitle(String title) {
        return countryRepo.findCompanyTypeByTitleContainingIgnoreCase(title);
    }

    public void editCountry(final CountryDto countryDto) {

        getCountryById(countryDto.getCountryId()).setTitle(countryDto.getTitle());
    }

    public void deleteCountry(Long id) {

        countryRepo.delete(getCountryById(id));
    }
}
