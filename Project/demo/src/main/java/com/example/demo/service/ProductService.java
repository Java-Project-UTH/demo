package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Tìm sản phẩm theo ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Tìm sản phẩm theo danh mục (dụng cụ thể thao, đồ ăn, nước uống)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Tìm sản phẩm theo từ khóa trong tên
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // Thêm mới hoặc cập nhật sản phẩm
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

