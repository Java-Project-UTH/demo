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
        Product p1 = new Product("Vợt Pickleball", "Vợt chất lượng cao", 100.0, "/assets/img/vot.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);

        Product p2 = new Product("Bóng Pickleball", "Bóng tiêu chuẩn", 10.0, "/assets/img/bong.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p3 = new Product("Giày chơi Pickleball", "Giày chất lượng ccao", 200.0, "/assets/img/giay.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p4 = new Product("Trang phục thể thao", "Trang phục chất liệu cotton thoải mái", 300.0, "/assets/img/quanao.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p5 = new Product("Băng cổ tay", "Băng cổ tay thấm hút mồ hôi", 10.0, "/assets/img/bangcotay.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p6 = new Product("Băng đeo đầu gối ", "Bảo vệ các khớp", 100.0, "/assets/img/bangdaugoi.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p7 = new Product("Băng đeo khuỷu tay", "Bảo vệ khuỷu tay của bạn", 100.0, "/assets/img/bangkhuyutay.jpg", Product.ProductCategory.SPORTS_EQUIPMENT);
        Product p8 = new Product("Nước suối", "Chai 500ml", 5.0, "/assets/img/nuocsuoi.jpg", Product.ProductCategory.BEVERAGE);
        Product p9 = new Product("Nước tăng lực", "Chai 100ml", 10.0, "/assets/img/nuoctangluc.jpg", Product.ProductCategory.BEVERAGE);
        Product p10= new Product("Nước ngọt", "Chai 200ml", 15.0, "/assets/img/nuocngot.jpg", Product.ProductCategory.BEVERAGE);
        Product p11 = new Product("Nước khoáng", "Chai 500ml", 20.0, "/assets/img/nuockhoang.jpg", Product.ProductCategory.BEVERAGE);
        Product p12 = new Product("Bánh mì", "Bánh mì ngọt", 5.0, "/assets/img/banhmi.jpg", Product.ProductCategory.FOOD);
        Product p13 = new Product("Snack", "Snack khoai tây", 5.0, "/assets/img/snack.jpg", Product.ProductCategory.FOOD);
        Product p14 = new Product("Cơm cháy", "Cơm cháy chà bông", 5.0, "/assets/img/comchay.jpg", Product.ProductCategory.FOOD);
        Product p15 = new Product("Bánh bao", "Bánh bao nhân thịt trứng", 35.0, "/assets/img/banhbao.jpg", Product.ProductCategory.FOOD);

        // Lưu sản phẩm vào DB
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));

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

