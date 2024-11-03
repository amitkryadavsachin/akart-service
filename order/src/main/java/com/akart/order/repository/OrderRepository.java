package com.akart.order.repository;
import com.akart.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findAllOrderByUserId(Long userId);
}
