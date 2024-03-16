package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.ProductDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.ProductCategory;
import com.massonus.rccnavigator.entity.Rating;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import com.massonus.rccnavigator.repo.WishObjectRepo;
import com.massonus.rccnavigator.repo.WishRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepo productRepo;
    private CompanyRepo companyRepo;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productRepo = mock(ProductRepo.class);
        companyRepo = mock(CompanyRepo.class);
        productService = new ProductService(productRepo, companyRepo);

        productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductCategory(ProductCategory.MEAL);
        productDto.setTitle("test");
        productDto.setPrice(21.3);
        productDto.setCompanyId(1L);
        productDto.setImageLink("");
    }

    @Test
    void saveProduct() {
        Company company = new Company();
        when(companyRepo.findCompanyById(productDto.getCompanyId())).thenReturn(company);
        productService.saveProduct(productDto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        Assertions.assertEquals(savedProduct.getPrice(), productDto.getPrice());
    }

    @Test
    void editProduct() {
        Product product = new Product();
        when(productRepo.findProductById(productDto.getProductId())).thenReturn(product);
        productService.editProduct(productDto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        Assertions.assertEquals(savedProduct.getPrice(), productDto.getPrice());
    }

    @Test
    void getProductsInPage() {
    }

    @Test
    void setProductImage() {
    }

    @Test
    void testSetProductImage() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByTitleAndCompanyId() {
    }

    @Test
    void getAllProductsByCompanyId() {
    }
}