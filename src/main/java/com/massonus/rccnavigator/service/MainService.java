package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MainService {

    private final CompanyService companyService;
    private final ProductService productService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyTypeService companyTypeService;

    @Autowired
    public MainService(CompanyService companyService, ProductService productService, KitchenCategoryService kitchenCategoryService, CompanyTypeService companyTypeService) {
        this.companyService = companyService;
        this.productService = productService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyTypeService = companyTypeService;
    }

    public void initialize() {

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

        for (int i = 0; i < 5; i++) {

            Company company = new Company();

            switch (i) {

                case 1:
                    company.setImageLink("https://www.reston.ua/images/img/rukolla_1.jpg");
                    company.setTitle("Ternoso");
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("ITALIAN"));
                    company.setCompanyType(companyTypeService.getTypeByTitle("RESTAURANT"));
                    company.setPriceCategory(PriceCategory.HIGH);
                    break;

                case 2:

                    company.setImageLink("https://ic.pics.livejournal.com/terranova2017/56708048/1935389/1935389_original.jpg");
                    company.setTitle("American style");
                    company.setCompanyType(companyTypeService.getTypeByTitle("CAFFE"));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("AMERICAN"));
                    company.setPriceCategory(PriceCategory.MEDIUM);
                    break;

                case 3:
                    company.setImageLink("https://gcdn.tomesto.ru/img/place/000/030/551/restoran-turgenev-na-semakova_382c3_logo-338787.jpg");
                    company.setTitle("Palyanitsya");
                    company.setCompanyType(companyTypeService.getTypeByTitle("CAFFE"));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("UKRAINIAN"));
                    company.setPriceCategory(PriceCategory.MEDIUM);
                    break;

                case 4:
                    company.setImageLink("https://etvisa.com.ua/wp-content/uploads/2019/09/Marquee-Nightclub-768x487.jpg");
                    company.setTitle("Arizona");
                    company.setCompanyType(companyTypeService.getTypeByTitle("CLUB"));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("UKRAINIAN"));
                    company.setPriceCategory(PriceCategory.PREMIUM);
                    break;

                default:
                    company.setImageLink("https://moscowseasons.com/uploads/2019/06/26/5d13157c70b0b.jpeg");
                    company.setTitle("Teremok");
                    company.setCompanyType(companyTypeService.getTypeByTitle("GENDEL"));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("RUSSIAN"));
                    company.setPriceCategory(PriceCategory.LOW);
                    break;

            }

            companyService.saveCompany(company);
            createAndFillProductsListForCompany(company);
        }
    }

    private void createAndFillProductsListForCompany(final Company company) {
        Random random = new Random();

        switch (company.getKitchenCategory().getTitle().toUpperCase()) {

            case "ITALIAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getItalianProduct(company, i);
                    productService.saveProduct(product);
                }
                break;

            case "AMERICAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getAmericanProduct(company, i);
                    productService.saveProduct(product);

                }
                break;

            case "UKRAINIAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getUkrainianProduct(company, i);
                    productService.saveProduct(product);
                }
                break;

            default:

                for (int i = 0; i < random.nextInt(1, 3); i++) {
                    Product product = new Product();
                    product.setTitle("default");
                    product.setPrice(21 + " $");
                    product.setCompany(company);
                    productService.saveProduct(product);
                }
        }
    }

    private Product getAmericanProduct(Company company, int i) {
        Product product = new Product();

        switch (i) {

            case 1:
                product.setTitle("Apple pie");
                product.setPrice(28 + " $");
                product.setImageLink("https://zira.uz/wp-content/uploads/2018/11/italyanskiy-pirog-3.jpg");
                break;

            case 2:
                product.setTitle("Reuben sandwich");
                product.setPrice(9 + " $");
                product.setImageLink("https://s1.eda.ru/StaticContent/Photos/170220181326/201229122305/p_O.jpg");
                break;

            case 3:
                product.setTitle("Buffalo chicken wings");
                product.setPrice(43 + " $");
                product.setImageLink("https://foodies.academy/wp-content/uploads/2020/05/franks_redhot_buffalo_chicken_wings_043-601x601.jpg");
                break;
        }

        product.setCompany(company);
        return product;
    }

    private Product getItalianProduct(Company company, int i) {
        Product product = new Product();

        switch (i) {

            case 1:
                product.setTitle("Risotto");
                product.setPrice(24 + " $");
                product.setImageLink("https://img.iamcook.ru/2021/upl/recipes/cat/u-3e13c39cc46b1a2bf1b0f86bdbd75873.jpg");
                break;

            case 2:
                product.setTitle("Lasagna");
                product.setPrice(13 + " $");
                product.setImageLink("https://fasol.tv/upload/iblock/f03/7tpqjk37izsfegtztrptl0yw0puvaomq.jpg");
                break;

            case 3:
                product.setTitle("Gelato");
                product.setPrice(10 + " $");
                product.setImageLink("https://img07.rl0.ru/afisha/e1200x1200i/daily.afisha.ru/uploads/images/a/cb/acba09cfc23be0751686e9bd50cbdf64.jpg");
                break;

        }

        product.setCompany(company);
        return product;
    }

    private Product getUkrainianProduct(Company company, int i) {
        Product product = new Product();

        switch (i) {

            case 1:
                if (company.getCompanyType().getTitle().equals("CLUB")) {
                    product.setTitle("Mojito");
                    product.setPrice(69 + " $");
                    product.setImageLink("https://lenta.servicecdn.ru/globalassets/recepty/k/kokteyl-mohito/_.jpg?preset=medium");
                } else {
                    product.setTitle("Verguny");
                    product.setPrice(28 + " $");
                    product.setImageLink("https://yasensvit.ua/uploads/recipes/prev/630e3d8f9de48.jpg");
                }
                break;

            case 2:
                if (company.getCompanyType().getTitle().equals("CLUB")) {
                    product.setTitle("whiskey");
                    product.setPrice(132 + " $");
                    product.setImageLink("https://uc.kr.ua/wp-content/uploads/2023/07/Viski-v-bokalah.jpg");
                } else {


                    product.setTitle("Dumplings");
                    product.setPrice(9 + " $");
                    product.setImageLink("https://img-global.cpcdn.com/recipes/4fba9f5e462fbf1d/680x482cq70/postnyie-pampushki-s-chiesnokom-%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D0%B5-%D1%84%D0%BE%D1%82%D0%BE-%D1%80%D0%B5%D1%86%D0%B5%D0%BF%D1%82%D0%B0.jpg");
                }
                break;

            case 3:
                if (company.getCompanyType().getTitle().equals("CLUB")) {
                    product.setTitle("Raspberry cocktail");
                    product.setPrice(43 + " $");
                    product.setImageLink("https://coffeeynya.ua/image/cache/catalog/Recipes/malynovyi_kokteil_recept-1-chtoprigotovit.ru-1200x900.jpg");
                } else {
                    product.setTitle("Galushki");
                    product.setPrice(43 + " $");
                    product.setImageLink("https://www.patee.ru/r/x6/03/a2/21/960m.jpg");

                }
                break;
        }
        product.setCompany(company);
        return product;
    }
}
