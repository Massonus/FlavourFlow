package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    Set<Product> findProductsByCompanyId(Long companyId);

    Set<Product> findOneTypeOfProductById(Long id);

    Set<Product> findProductsByTitleContainingIgnoreCase(String title);

}
