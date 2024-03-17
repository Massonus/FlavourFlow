package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.ProductDto;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.ProductCategory;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CompanyRepo companyRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CompanyRepo companyRepo) {
        this.productRepo = productRepo;
        this.companyRepo = companyRepo;
    }

    public ProductDto saveProduct(final ProductDto productDto) {
        Product product = new Product();

        if (!productDto.getImageLink().isEmpty()) {
            product.setImageLink(productDto.getImageLink());
            product.setImage(null);
        }

        product.setProductCategory(productDto.getProductCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCompany(companyRepo.findCompanyById(productDto.getCompanyId()));

        productRepo.save(product);
        return productDto;
    }

    public ProductDto editProduct(final ProductDto productDto) {
        Product savedProduct = getProductById(productDto.getProductId());

        if (!productDto.getImageLink().isEmpty()) {
            savedProduct.setImageLink(productDto.getImageLink());
            savedProduct.setImage(null);
        }

        savedProduct.setProductCategory(productDto.getProductCategory());
        savedProduct.setTitle(productDto.getTitle());
        savedProduct.setPrice(productDto.getPrice());

        productRepo.save(savedProduct);
        return productDto;

    }

    public Page<Product> getProductsInPage(final Long companyId, final Pageable pageable, final String sort, final ProductCategory productCategory) {

        List<Product> products = getAllProductsByCompanyId(companyId);

        if (Objects.nonNull(productCategory)) {
            products = filterProductsByProductCategory(products, productCategory);
        }

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

            default -> products.stream()
                    .sorted(Comparator.comparing(Product::getId))
                    .toList();
        };

        return products;

    }

    private List<Product> filterProductsByProductCategory(final List<Product> products, final ProductCategory productCategory) {
        return products.stream()
                .filter(p -> p.getProductCategory().equals(productCategory))
                .toList();
    }

    public void setProductImage(final String title, final Long companyId, final Image image) {
        getProductByTitleAndCompanyId(title, companyId).setImage(image);
    }

    public void setProductImage(final Long productId, final Image image) {
        getProductById(productId).setImage(image);
    }

    public void deleteProduct(final Product product) {
        productRepo.delete(product);
    }

    public Product getProductById(final Long id) {
        return productRepo.findProductById(id);
    }

    public Product getProductByTitleAndCompanyId(String title, Long companyId) {
        return productRepo.findProductByTitleAndCompanyId(title, companyId);
    }

    public List<Product> getAllProductsByCompanyId(final Long companyId) {
        return productRepo.findProductsByCompanyId(companyId);
    }

}
