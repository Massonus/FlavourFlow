package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderObjectRepo extends JpaRepository<OrderObject, Long> {

}
