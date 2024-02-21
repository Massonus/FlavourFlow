package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.entity.PriceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MainService {

    private final CompanyService companyService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyTypeService companyTypeService;

    @Autowired
    public MainService(CompanyService companyService, KitchenCategoryService kitchenCategoryService, CompanyTypeService companyTypeService) {
        this.companyService = companyService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyTypeService = companyTypeService;
    }

    public Set<Company> initialize() {

        for (int i = 1; i < 5; i++) {

            KitchenCategory kitchenCategory = new KitchenCategory();
            CompanyType companyType = new CompanyType();

            switch (i) {

                case 1:
                    kitchenCategory.setTitle("ITALIAN");
                    companyType.setTitle("CAFFE");
                    break;

                case 2:
                    kitchenCategory.setTitle("UKRAINIAN");
                    companyType.setTitle("CLUB");
                    break;

                case 3:
                    kitchenCategory.setTitle("AMERICAN");
                    companyType.setTitle("RESTAURANT");
                    break;

                case 4:
                    kitchenCategory.setTitle("RUSSIAN");
                    companyType.setTitle("GENDEL");
            }

            kitchenCategoryService.saveKitchenCategory(kitchenCategory);
            companyTypeService.saveCompanyType(companyType);

        }

        Set<Company> companies = new HashSet<>();
        for (int i = 0; i < 5; i++) {

            Company company = new Company();

            switch (i) {

                case 1:
                    company.setImageLink("https://www.reston.ua/images/img/rukolla_1.jpg");
                    company.setTitle("Ternoso");
                    company.setCompanyType(companyTypeService.getTypeById(1L));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryById(1L));
                    company.setPriceCategory(PriceCategory.HIGH);
                    break;

                case 2:

                    company.setImageLink("https://ic.pics.livejournal.com/terranova2017/56708048/1935389/1935389_original.jpg");
                    company.setTitle("American style");
                    company.setCompanyType(companyTypeService.getTypeById(2L));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryById(2L));
                    company.setPriceCategory(PriceCategory.MEDIUM);
                    break;

                case 3:
                    company.setImageLink("https://etvisa.com.ua/wp-content/uploads/2019/09/Marquee-Nightclub-768x487.jpg");
                    company.setTitle("Palyanica");
                    company.setCompanyType(companyTypeService.getTypeById(3L));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryById(3L));
                    company.setPriceCategory(PriceCategory.PREMIUM);
                    break;

                case 4:
                    company.setImageLink("https://moscowseasons.com/uploads/2019/06/26/5d13157c70b0b.jpeg");
                    company.setTitle("Arizona");
                    company.setCompanyType(companyTypeService.getTypeById(4L));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryById(4L));
                    company.setPriceCategory(PriceCategory.PREMIUM);
                    break;

                default:
                    company.setImageLink("https://gcdn.tomesto.ru/img/place/000/030/551/restoran-turgenev-na-semakova_382c3_logo-338787.jpg");
                    company.setTitle("Teremok");
                    company.setCompanyType(companyTypeService.getTypeById(1L));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryById(2L));
                    company.setPriceCategory(PriceCategory.LOW);
                    break;

            }

            companyService.saveCompany(company);
            companyService.createAndFillProductsListForCompany(company);
            companies.add(company);
        }
        return companies;
    }
}
