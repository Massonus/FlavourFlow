package com.massonus.rccnavigator.service;

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

    public void saveCompanyType(final CompanyCountry companyCountry) {

        countryRepo.save(companyCountry);
    }


    public Set<CompanyCountry> getAllCountries() {

        return new HashSet<>(countryRepo.findAll());
    }

    public CompanyCountry getTypeById(Long id) {
        return countryRepo.findCompanyTypeById(id);
    }

    public CompanyCountry getCountryByTitle(String title) {
        return countryRepo.findCompanyTypeByTitleContainingIgnoreCase(title);
    }

    public void editType(Long id, String title) {

        getTypeById(id).setTitle(title);
    }

    public void deleteType(Long id) {

        countryRepo.delete(getTypeById(id));
    }
}
