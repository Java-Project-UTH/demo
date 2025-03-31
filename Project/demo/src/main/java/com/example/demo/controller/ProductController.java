package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/list";  // Hiển thị trang danh sách sản phẩm (Thymeleaf)
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/detail"; // Hiển thị trang chi tiết sản phẩm
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}

