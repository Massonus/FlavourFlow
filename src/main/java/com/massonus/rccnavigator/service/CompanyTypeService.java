package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.repo.CompanyTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyTypeService {

    private final CompanyTypeRepo companyTypeRepo;

    @Autowired
    public CompanyTypeService(CompanyTypeRepo companyTypeRepo) {
        this.companyTypeRepo = companyTypeRepo;
    }

    public void saveCompanyType(final CompanyType companyType) {

        companyTypeRepo.save(companyType);
    }


    public Set<CompanyType> getAllTypes() {

        return new HashSet<>(companyTypeRepo.findAll());
    }

    public CompanyType getTypeById(Long id) {
        return companyTypeRepo.findCompanyTypeById(id);
    }

    public CompanyType getTypeByTitle(String title) {
        return companyTypeRepo.findCompanyTypeByTitle(title);
    }

    public void editType(Long id, String title) {

        getTypeById(id).setTitle(title);
    }

    public void deleteType(Long id) {

        companyTypeRepo.delete(getTypeById(id));
    }
}
