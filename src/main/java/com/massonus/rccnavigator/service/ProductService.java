package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
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

    public void deleteProduct(final Product product) {
        productRepo.delete(product);
    }

    public Product getProductById(final Long id) {
        return productRepo.findProductById(id);
    }

    public Set<Product> getAllProducts() {

        return new HashSet<>(productRepo.findAll());
    }

    public Set<Product> getAllProductsByCompanyId(final Long companyId) {
        return productRepo.findProductsByCompanyId(companyId);
    }

    public Set<Product> getAllProductsByTitleContainingIgnoreCase(String title) {
        return productRepo.findProductsByTitleContainingIgnoreCase(title);
    }

}
