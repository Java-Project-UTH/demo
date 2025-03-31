package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm đơn hàng theo email khách hàng
    List<Order> findByEmail(String email);

    // Tìm đơn hàng theo số điện thoại khách hàng
    List<Order> findByPhone(String phone);

    // Tìm đơn hàng theo trạng thái (PENDING, COMPLETED, CANCELLED)
    List<Order> findByStatus(String status);
}

