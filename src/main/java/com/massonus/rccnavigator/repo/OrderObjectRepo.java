package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderObjectRepo extends JpaRepository<OrderObject, Long> {

    List<OrderObject> findOrderObjectsByUserId(Long userId);
}
