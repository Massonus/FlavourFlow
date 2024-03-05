package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.repo.CompanyCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyCountryService {

    private final CompanyCountryRepo companyCountryRepo;

    @Autowired
    public CompanyCountryService(CompanyCountryRepo companyCountryRepo) {
        this.companyCountryRepo = companyCountryRepo;
    }

    public void saveCompanyType(final CompanyCountry companyCountry) {

        companyCountryRepo.save(companyCountry);
    }


    public Set<CompanyCountry> getAllCountries() {

        return new HashSet<>(companyCountryRepo.findAll());
    }

    public CompanyCountry getTypeById(Long id) {
        return companyCountryRepo.findCompanyTypeById(id);
    }

    public CompanyCountry getCountryByTitle(String title) {
        return companyCountryRepo.findCompanyTypeByTitleContainingIgnoreCase(title);
    }

    public void editType(Long id, String title) {

        getTypeById(id).setTitle(title);
    }

    public void deleteType(Long id) {

        companyCountryRepo.delete(getTypeById(id));
    }
}
