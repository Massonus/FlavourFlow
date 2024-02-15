package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    private final UserService userService;

    @Autowired
    public ProductService(ProductRepo productRepo, UserService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }

    public void saveProduct(final String title, final String price, final Image image) {
        Product product = new Product();
        product.setTitle(title);
        product.setImage(image);
        product.setPrice(price);
        productRepo.save(product);
    }

    public void edit(Long id, String title, String price) {
        Product savedProduct = productRepo.findProductById(id);

        savedProduct.setTitle(title);
        savedProduct.setPrice(price);

        productRepo.save(savedProduct);
    }

    public Product getProductById(final Long id) {
        return productRepo.findProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

}
