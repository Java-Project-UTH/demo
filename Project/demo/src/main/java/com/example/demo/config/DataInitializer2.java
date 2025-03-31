package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;

@Component
public class DataInitializer2 implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void run(String... args) throws Exception {
        // Xóa dữ liệu cũ để tránh trùng lặp
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();

        // Tạo danh sách sản phẩm mẫu
        Product p1 = new Product("Vợt Pickleball", "Vợt chất lượng cao", 100.0, "/images/vot.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p2 = new Product("Bóng Pickleball", "Bóng tiêu chuẩn", 10.0, "/images/bong.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p3 = new Product("Nước suối", "Chai 500ml", 5.0, "/images/nuoc.jpg", Product.ProductCategory.BEVERAGE);

        // Lưu sản phẩm vào DB
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        // Tạo đơn hàng mẫu
        Order order1 = new Order("Nguyễn Văn A", "a@gmail.com", "0123456789", "Hà Nội", Order.OrderStatus.PENDING);
        Order order2 = new Order("Trần Thị B", "b@gmail.com", "0987654321", "TP.HCM", Order.OrderStatus.COMPLETED);

        // Lưu đơn hàng vào DB
        orderRepository.saveAll(Arrays.asList(order1, order2));

        // Tạo chi tiết đơn hàng
        OrderDetail od1 = new OrderDetail(order1, p1, 1, p1.getPrice());
        OrderDetail od2 = new OrderDetail(order1, p2, 2, p2.getPrice() * 2);
        OrderDetail od3 = new OrderDetail(order2, p3, 3, p3.getPrice() * 3);

        // Lưu chi tiết đơn hàng vào DB
        orderDetailRepository.saveAll(Arrays.asList(od1, od2, od3));

        System.out.println("Dữ liệu mẫu đã được khởi tạo!");
    }
}

