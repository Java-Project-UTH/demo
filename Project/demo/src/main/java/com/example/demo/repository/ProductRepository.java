
package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm sản phẩm theo danh mục
    List<Product> findByCategory(String category);

    // Tìm sản phẩm theo tên (tìm kiếm gần đúng)
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // Tìm sản phẩm có giá trong khoảng
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
