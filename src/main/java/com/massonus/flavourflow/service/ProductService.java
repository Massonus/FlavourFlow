package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.ImageResponseDto;
import com.massonus.flavourflow.dto.ProductDto;
import com.massonus.flavourflow.entity.Product;
import com.massonus.flavourflow.entity.ProductCategory;
import com.massonus.flavourflow.repo.CompanyRepo;
import com.massonus.flavourflow.repo.ProductRepo;
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
    private final ImageService imageService;

    @Autowired
    public ProductService(ProductRepo productRepo, CompanyRepo companyRepo, ImageService imageService) {
        this.productRepo = productRepo;
        this.companyRepo = companyRepo;
        this.imageService = imageService;
    }

    public ProductDto saveProduct(final ProductDto productDto) {
        Product product = new Product();

        if (!productDto.getImageLink().isEmpty()) {
            product.setImageLink(productDto.getImageLink());
        }

        product.setProductCategory(productDto.getProductCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCompany(companyRepo.findCompanyById(productDto.getCompanyId()));
        product.setIsDropdownImage(false);

        Product save = productRepo.save(product);
        productDto.setProductId(save.getId());
        return productDto;
    }

    public ProductDto editProduct(final ProductDto productDto) {
        Product savedProduct = getProductById(productDto.getProductId());

        if (savedProduct.getIsDropdownImage()) {
            deleteProductImage(savedProduct);
        }

        if (!productDto.getImageLink().isEmpty()) {
            savedProduct.setImageLink(productDto.getImageLink());
            deleteProductImage(savedProduct);
            savedProduct.setIsDropdownImage(false);
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

    public void setProductImage(final Long productId, final ImageResponseDto responseDto) {
        Product productById = getProductById(productId);
        productById.setImageLink(responseDto.getUrl());
        productById.setIsDropdownImage(true);
    }

    public ImageResponseDto deleteProductImage(final Product product) {
        if (!product.getIsDropdownImage()) {
            ImageResponseDto imageResponseDto = new ImageResponseDto();
            imageResponseDto.setStatus(200);
            return imageResponseDto;
        }
        return imageService.deleteImage("product".toUpperCase(), product.getId());
    }

    public ImageResponseDto deleteProduct(final Long productId) {
        Product productById = getProductById(productId);


        ImageResponseDto imageResponseDto = new ImageResponseDto();

        if (productById.getIsDropdownImage()) {
            imageResponseDto = deleteProductImage(productById);
            if (imageResponseDto.getStatus() == 500) {
                return imageResponseDto;
            }
        }

        imageResponseDto.setStatus(200);
        productRepo.delete(productById);
        return imageResponseDto;
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

    public List<Product> getProductsByTitleContainingIgnoreCase(final String title) {
        return productRepo.findProductsByTitleContainingIgnoreCase(title);
    }

}
