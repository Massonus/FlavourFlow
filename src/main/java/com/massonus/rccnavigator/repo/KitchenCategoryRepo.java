package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.KitchenCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenCategoryRepo extends JpaRepository<KitchenCategory, Long> {


}
