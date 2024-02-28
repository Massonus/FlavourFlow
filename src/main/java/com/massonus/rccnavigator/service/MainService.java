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
                    kitchenCategory.setTitle("SPANISH");
                    companyType.setTitle("FAST FOOD");
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
                    company.setImageLink("https://st.hzcdn.com/simgs/1461b8f30a2d35c5_4-8890/home-design.jpg");
                    company.setTitle("PNS");
                    company.setCompanyType(companyTypeService.getTypeByTitle("FAST FOOD"));
                    company.setKitchenCategory(kitchenCategoryService.getCategoryByTitle("SPANISH"));
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

            case "SPANISH":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getSpanishProduct(company, i);
                    productService.saveProduct(product);
                }
                break;
        }
    }

    private Product getAmericanProduct(Company company, int i) {
        Product product = new Product();

        switch (i) {

            case 1:
                product.setTitle("Apple pie");
                product.setPrice("28");
                product.setImageLink("https://zira.uz/wp-content/uploads/2018/11/italyanskiy-pirog-3.jpg");
                break;

            case 2:
                product.setTitle("Reuben sandwich");
                product.setPrice("9");
                product.setImageLink("https://s1.eda.ru/StaticContent/Photos/170220181326/201229122305/p_O.jpg");
                break;

            case 3:
                product.setTitle("Buffalo chicken wings");
                product.setPrice("43");
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
                product.setPrice("24");
                product.setImageLink("https://static01.nyt.com/images/2024/01/10/multimedia/10Risotto-gcmz/10Risotto-gcmz-superJumbo.jpg");
                break;

            case 2:
                product.setTitle("Lasagna");
                product.setPrice("13");
                product.setImageLink("https://images.ctfassets.net/hhv516v5f7sj/1AHBnS81eRgSBQwnkub3kF/f6a5d243c45d122ba4d419456c931d5c/lasagnabeeflite_1000x1000.jpg?fm=webp&q=90&w=3840");
                break;

            case 3:
                product.setTitle("Gelato");
                product.setPrice("10");
                product.setImageLink("https://www.watermelon.org/wp-content/uploads/2021/05/WATERMELON-GELATO-017-1x1-1.jpg");
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
                    product.setPrice("69");
                    product.setImageLink("https://lenta.servicecdn.ru/globalassets/recepty/k/kokteyl-mohito/_.jpg?preset=medium");
                } else {
                    product.setTitle("Verguny");
                    product.setPrice("28");
                    product.setImageLink("https://yasensvit.ua/uploads/recipes/prev/630e3d8f9de48.jpg");
                }
                break;

            case 2:
                if (company.getCompanyType().getTitle().equals("CLUB")) {
                    product.setTitle("whiskey");
                    product.setPrice("132");
                    product.setImageLink("https://uc.kr.ua/wp-content/uploads/2023/07/Viski-v-bokalah.jpg");
                } else {


                    product.setTitle("Dumplings");
                    product.setPrice("9");
                    product.setImageLink("https://img-global.cpcdn.com/recipes/4fba9f5e462fbf1d/680x482cq70/postnyie-pampushki-s-chiesnokom-%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D0%B5-%D1%84%D0%BE%D1%82%D0%BE-%D1%80%D0%B5%D1%86%D0%B5%D0%BF%D1%82%D0%B0.jpg");
                }
                break;

            case 3:
                if (company.getCompanyType().getTitle().equals("CLUB")) {
                    product.setTitle("Raspberry cocktail");
                    product.setPrice("43");
                    product.setImageLink("https://coffeeynya.ua/image/cache/catalog/Recipes/malynovyi_kokteil_recept-1-chtoprigotovit.ru-1200x900.jpg");
                } else {
                    product.setTitle("Galushki");
                    product.setPrice("43");
                    product.setImageLink("https://www.patee.ru/r/x6/03/a2/21/960m.jpg");

                }
                break;
        }
        product.setCompany(company);
        return product;
    }

    private Product getSpanishProduct(Company company, int i) {
        Product product = new Product();

        switch (i) {

            case 1:
                product.setTitle("Pizza");
                product.setPrice("5");
                product.setImageLink("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Supreme_pizza.jpg/1200px-Supreme_pizza.jpg");
                break;

            case 2:
                product.setTitle("Nuggets");
                product.setPrice("4");
                product.setImageLink("https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Chicken_Nuggets.jpg/640px-Chicken_Nuggets.jpg");
                break;

            case 3:
                product.setTitle("Hamburger");
                product.setPrice("8");
                product.setImageLink("https://i.obozrevatel.com/food/recipemain/2020/2/12/14.png?size=636x424");
                break;
        }

        product.setCompany(company);
        return product;
    }
}
