package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderObjectRepo extends JpaRepository<OrderObject, Long> {

}
