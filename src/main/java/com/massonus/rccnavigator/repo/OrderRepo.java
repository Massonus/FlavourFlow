package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);
}
