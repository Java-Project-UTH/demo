package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "User"; // Hiển thị trang đăng ký / đăng nhập
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String password,
                               Model model) {
        if (name.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Username and password cannot be empty");
            return "User";
        }

        User newUser = new User(name, password);
        userService.registerUser(newUser);

        return "redirect:/users/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String name,
                            @RequestParam String password,
                            Model model) {
        Optional<User> user = userService.loginUser(name, password);
        if (user.isPresent()) {
            return "redirect:/dashboard"; // Điều hướng sau khi đăng nhập thành công
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "User";
        }
    }
}