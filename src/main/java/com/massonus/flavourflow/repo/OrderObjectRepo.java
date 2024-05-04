package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderObjectRepo extends JpaRepository<OrderObject, Long> {

    List<OrderObject> findOrderObjectsByOrderId(Long orderId);
}
