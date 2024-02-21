package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    private final ProductService productService;
    private final ImageService imageService;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, ProductService productService, ImageService imageService) {
        this.companyRepo = companyRepo;
        this.productService = productService;
        this.imageService = imageService;
    }

    public Company saveCompany(final Company validCompany, final Image image, final KitchenCategory category, CompanyType type) {
        Company company = new Company();
        company.setTitle(validCompany.getTitle());
        company.setImage(image);
        company.setCompanyType(type);
        company.setPriceCategory(validCompany.getPriceCategory());
        company.setProducts(validCompany.getProducts());
        company.setKitchenCategory(category);

        companyRepo.save(company);

        return company;
    }

    public void editCompany(final Long id, final Company company, KitchenCategory category, CompanyType type, MultipartFile multipartFile, String imageLink) {
        Company savedCompany = companyRepo.findCompanyById(id);


        if (!multipartFile.isEmpty()) {
            Image uploadImage;
            try {
                uploadImage = imageService.upload(multipartFile);
                savedCompany.setImage(uploadImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!imageLink.isEmpty()) {
            savedCompany.setImageLink(imageLink);
            savedCompany.setImage(null);
        }

        savedCompany.setTitle(company.getTitle());
        savedCompany.setPriceCategory(company.getPriceCategory());
        savedCompany.setCompanyType(type);
        savedCompany.setKitchenCategory(category);
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

    public Set<Company> getCompaniesByCategoryId(Long categoryId) {
        return companyRepo.findCompaniesByKitchenCategoryId(categoryId);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Set<Company> getAllCompaniesByTitleContainingIgnoreCase(final String title) {
        return companyRepo.findCompaniesByTitleContainingIgnoreCase(title);
    }

    public Set<Product> createAndFillProductsListForCompany(final Company company) {
        Set<Product> products = new HashSet<>();
        Random random = new Random();

        switch (company.getKitchenCategory().getTitle().toUpperCase()) {

            case "ITALIAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getItalianProduct(company, i);

                    productService.saveProduct(product);
                    products.add(product);


                }
                break;

            case "AMERICAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getAmericanProduct(company, i);

                    productService.saveProduct(product);
                    products.add(product);

                }
                break;

            case "UKRAINIAN":

                for (int i = 1; i < random.nextInt(2, 4); i++) {
                    Product product = getUkrainianProduct(company, i);

                    product.setCompany(company);

                    productService.saveProduct(product);
                    products.add(product);

                }
                break;

            default:

                for (int i = 0; i < random.nextInt(1, 3); i++) {
                    Product product = new Product();
                    product.setTitle("default");
                    product.setPrice(21 + " $");
                    product.setCompany(company);
                    productService.saveProduct(product);
                    products.add(product);
                }
        }

        return products;
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