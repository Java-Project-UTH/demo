package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    // Hiển thị danh sách đơn hàng
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    // Xem chi tiết đơn hàng
    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.getOrderById(id);
        List<OrderDetail> orderDetails = order.get().getOrderDetails();
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "order/detail";
    }

    // Hiển thị form đặt hàng
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("order", new Order());
        return "order/form";
    }

    // Xử lý đặt hàng
    @PostMapping("/save")
    public String placeOrder(@ModelAttribute Order order) {
        OrderService.saveOrder(order);
        return "redirect:/orders";
    }

    // Xử lý thanh toán qua VNPay
    //@GetMapping("/pay/{id}")
   // public String payOrder(@PathVariable Long id) {
    //    OrderService.processPayment(id, "VNPay"); // Giả sử thanh toán bằng VNPay
    //    return "redirect:/orders";
}

