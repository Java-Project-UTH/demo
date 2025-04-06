package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public static void saveOrder(Order order) {
    }

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Tìm đơn hàng theo ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Tìm đơn hàng theo email khách hàng
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    // Tạo mới đơn hàng
    public Order createOrder(Order order, List<OrderDetail> orderDetails) {
        Order savedOrder = orderRepository.save(order);

        // Lưu chi tiết đơn hàng
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(savedOrder);
            orderDetailRepository.save(detail);
        }

        return savedOrder;
    }

    // Cập nhật trạng thái đơn hàng (PENDING -> COMPLETED, CANCELLED)
    public Order updateOrderStatus(Long id, String status) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(Order.OrderStatus.valueOf(status));
            return orderRepository.save(order);
        }
        return null;
    }

    // Xóa đơn hàng theo ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

