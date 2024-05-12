package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.ProductDto;
import com.massonus.flavourflow.entity.Company;
import com.massonus.flavourflow.entity.Product;
import com.massonus.flavourflow.entity.ProductCategory;
import com.massonus.flavourflow.repo.CompanyRepo;
import com.massonus.flavourflow.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService target;
    private ProductRepo productRepo;

    private ProductDto productDto;
    private Product expectedProduct;
    private Company company;

    @BeforeEach
    void setUp() {
        productRepo = mock(ProductRepo.class);
        CompanyRepo companyRepo = mock(CompanyRepo.class);
        ImageService imageService = mock(ImageService.class);
        BasketObjectService basketObjectService = mock(BasketObjectService.class);
        WishObjectService wishObjectService = mock(WishObjectService.class);
        target = new ProductService(productRepo, companyRepo, imageService, basketObjectService, wishObjectService);

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
    void shouldEditProduct() {
        when(productRepo.findProductById(productDto.getProductId())).thenReturn(expectedProduct);
        target.editProduct(productDto);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepo, times(1)).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        Assertions.assertEquals(savedProduct.getPrice(), productDto.getPrice());
    }

    @Test
    void shouldGetProductById() {
        when(productRepo.findProductById(productDto.getProductId())).thenReturn(expectedProduct);

        Product productById = target.getProductById(productDto.getProductId());

        assertSame(productById.getId(), productDto.getProductId());
    }

    @Test
    void shouldGetProductByTitleAndCompanyId() {
        when(productRepo.findProductByTitleAndCompanyId(productDto.getTitle(), productDto.getCompanyId())).thenReturn(expectedProduct);

        Product product = target.getProductByTitleAndCompanyId(productDto.getTitle(), productDto.getCompanyId());

        assertSame(product.getTitle(), productDto.getTitle());
    }

    @Test
    void shouldGetAllProductsByCompanyId() {
        List<Product> products = List.of(new Product(company), new Product(company));
        when(productRepo.findProductsByCompanyId(company.getId())).thenReturn(products);

        List<Product> allProductsByCompanyId = target.getAllProductsByCompanyId(company.getId());

        assertEquals(allProductsByCompanyId.size(), 2);
    }
}