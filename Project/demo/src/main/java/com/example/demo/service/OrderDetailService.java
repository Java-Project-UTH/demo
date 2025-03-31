package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Lấy danh sách chi tiết đơn hàng theo orderId
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    // Tính tổng tiền của đơn hàng
    public double getTotalAmountByOrderId(Long orderId) {
        List<OrderDetail> details = orderDetailRepository.findByOrderId(orderId);
        return details.stream()
                .mapToDouble(d -> d.getQuantity() * d.getPrice())
                .sum();
    }

    // Lưu danh sách chi tiết đơn hàng
    public void saveOrderDetails(List<OrderDetail> details) {
        orderDetailRepository.saveAll(details);
    }
}

