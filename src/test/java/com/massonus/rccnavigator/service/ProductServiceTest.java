package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.ProductDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.ProductCategory;
import com.massonus.rccnavigator.repo.CompanyRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepo productRepo;
    private CompanyRepo companyRepo;

    private ProductDto productDto;
    private Product expectedProduct;
    private Company company;

    @BeforeEach
    void setUp() {
        productRepo = mock(ProductRepo.class);
        companyRepo = mock(CompanyRepo.class);
        productService = new ProductService(productRepo, companyRepo);

        productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductCategory(ProductCategory.MEAL);
        productDto.setTitle("PNS");
        productDto.setPrice(21.3);
        productDto.setCompanyId(1L);
        productDto.setImageLink("");

        company = new Company();
        company.setId(1L);

        expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("PNS");
        expectedProduct.setCompany(company);
    }

    @Test
    void saveProduct() {
        when(companyRepo.findCompanyById(productDto.getCompanyId())).thenReturn(company);
        productService.saveProduct(productDto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        Assertions.assertEquals(savedProduct.getPrice(), productDto.getPrice());
    }

    @Test
    void editProduct() {
        when(productRepo.findProductById(productDto.getProductId())).thenReturn(expectedProduct);
        productService.editProduct(productDto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        Assertions.assertEquals(savedProduct.getPrice(), productDto.getPrice());
    }

    @Test
    void getProductById() {
        when(productRepo.findProductById(productDto.getProductId())).thenReturn(expectedProduct);

        Product productById = productService.getProductById(productDto.getProductId());

        assertSame(productById.getId(), productDto.getProductId());
    }

    @Test
    void getProductByTitleAndCompanyId() {
        when(productRepo.findProductByTitleAndCompanyId(productDto.getTitle(), productDto.getCompanyId())).thenReturn(expectedProduct);

        Product product = productService.getProductByTitleAndCompanyId(productDto.getTitle(), productDto.getCompanyId());

        assertSame(product.getTitle(), productDto.getTitle());
    }

    @Test
    void getAllProductsByCompanyId() {
        List<Product> products = List.of(new Product(company), new Product(company));
        when(productRepo.findProductsByCompanyId(company.getId())).thenReturn(products);

        List<Product> allProductsByCompanyId = productService.getAllProductsByCompanyId(company.getId());

        assertEquals(allProductsByCompanyId.size(), 2);
    }
}