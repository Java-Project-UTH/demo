package com.example.demo.controller;

<<<<<<< Updated upstream
=======
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.AuthResponse;
>>>>>>> Stashed changes
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
<<<<<<< Updated upstream
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String name, @RequestParam String password) {
        Optional<User> user = userService.loginUser(name, password);
        return user.map(value -> ResponseEntity.ok("Login Successful!"))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
=======
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.ok(new AuthResponse("User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse("Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String jwt = jwtTokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse("Login failed: " + e.getMessage()));
        }
>>>>>>> Stashed changes
    }
}
