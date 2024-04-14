package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.KitchenCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenCategoryRepo extends JpaRepository<KitchenCategory, Long> {

    KitchenCategory findKitchenCategoryById(Long id);

    KitchenCategory findKitchenCategoryByTitleContainingIgnoreCase(String title);

}
