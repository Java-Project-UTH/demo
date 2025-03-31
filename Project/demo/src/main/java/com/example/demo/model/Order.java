package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private String customerAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Trạng thái đơn hàng (PENDING, COMPLETED, CANCELED)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    // Constructors
    public Order() {}

    public Order(String customerName, String customerEmail, String customerPhone, String customerAddress, OrderStatus status) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.status = status;
    }
    public enum OrderStatus {
        PENDING,    // Đang chờ xác nhận
        COMPLETED,  // Đã hoàn thành
        CANCELED    // Đã hủy
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    // Getters & Setters
}

