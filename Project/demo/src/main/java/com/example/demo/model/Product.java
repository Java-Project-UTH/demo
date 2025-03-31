package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String imageUrl; // URL ảnh sản phẩm

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category; // Danh mục sản phẩm

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    // Constructors
    public Product() {}

    public Product(String name, String description, double price, String imageUrl, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }
    public enum ProductCategory {
        SPORTS_EQUIPMENT, // Dụng cụ thể thao
        FOOD,             // Đồ ăn
        BEVERAGE          // Nước uống
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    // Getters & Setters
}

