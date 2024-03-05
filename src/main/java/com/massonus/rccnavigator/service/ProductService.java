package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
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
public class ProductService {

    private final ProductRepo productRepo;
    private final CompanyRepo companyRepo;
    private final ImageService imageService;

    @Autowired
    public ProductService(ProductRepo productRepo, CompanyRepo companyRepo, ImageService imageService) {
        this.productRepo = productRepo;
        this.companyRepo = companyRepo;
        this.imageService = imageService;
    }

    public void saveProduct(final Product validProduct, final MultipartFile multipartFile, final String imageLink, final Long companyId) {
        Product product = new Product();

        if (!multipartFile.isEmpty()) {
            Image uploadImage = imageService.upload(multipartFile);
            product.setImage(uploadImage);
        }

        if (!imageLink.isEmpty()) {
            product.setImageLink(imageLink);
            product.setImage(null);
        }

        product.setTitle(validProduct.getTitle());
        product.setPrice(validProduct.getPrice());
        product.setCompany(companyRepo.findCompanyById(companyId));

        productRepo.save(product);
    }

    public void saveProduct(final Product validProduct) {

        productRepo.save(validProduct);
    }

    public Long editProduct(final Long id, final Product product, final MultipartFile multipartFile, String imageLink) {
        Product savedProduct = productRepo.findProductById(id);

        if (!multipartFile.isEmpty()) {
            Image uploadImage = imageService.upload(multipartFile);
            savedProduct.setImage(uploadImage);
        }

        if (!imageLink.isEmpty()) {
            savedProduct.setImageLink(imageLink);
            savedProduct.setImage(null);
        }

        savedProduct.setTitle(product.getTitle());
        savedProduct.setPrice(product.getPrice());

        productRepo.save(savedProduct);
        return savedProduct.getCompany().getId();
    }

    public Page<Product> getProductsInPage(Long companyId, Pageable pageable, String sort) {

        List<Product> products = getAllProductsByCompanyId(companyId);

        /*if (Objects.nonNull(companyFilterDto.getCountryId())) {

            products = products.stream()
                    .filter(company -> company.getCompanyCountry().getId().equals(companyFilterDto.getCountryId()))
                    .toList();
        }

        if (Objects.nonNull(companyFilterDto.getCategoryId())) {

            products = products.stream()
                    .filter(company -> company.getKitchenCategory().getId().equals(companyFilterDto.getCategoryId()))
                    .toList();
        }*/

        if (Objects.nonNull(sort)) {
            products = getSortedProducts(sort, products);
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), products.size());

        return new PageImpl<>(products.subList(start, end), pageable, products.size());
    }

    private List<Product> getSortedProducts(String sort, List<Product> products) {

        products = switch (sort) {

            case "priceDesc" -> products.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();

            case "priceAsc" -> products.stream()
                    .sorted(Comparator.comparing(Product::getPrice).reversed())
                    .toList();

            case "nameA" -> products.stream()
                    .sorted(Comparator.comparing(Product::getTitle))
                    .toList();

            case "nameZ" -> products.stream()
                    .sorted(Comparator.comparing(Product::getTitle).reversed())
                    .toList();

            default -> products;
        };

        return products;

    }

    public void deleteProduct(final Product product) {
        productRepo.delete(product);
    }

    public Product getProductById(final Long id) {
        return productRepo.findProductById(id);
    }

    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    public List<Product> getAllProductsByCompanyId(final Long companyId) {
        return productRepo.findProductsByCompanyId(companyId);
    }

    public List<Product> getAllProductsByTitleContainingIgnoreCase(String title) {
        return productRepo.findProductsByTitleContainingIgnoreCase(title);
    }

}
