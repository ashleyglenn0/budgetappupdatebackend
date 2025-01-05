package com.budgetupdate.budgetupdate.Controllers;

import com.budgetupdate.budgetupdate.DTOs.LoginDTO;
import com.budgetupdate.budgetupdate.Models.User;
import com.budgetupdate.budgetupdate.Services.UserService;
import com.budgetupdate.budgetupdate.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userService.saveUser(user); // Save the user in the database
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sign-up failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Validate user credentials
            UserDetails userDetails = userService.loadUserByUsername(loginDTO.getEmail());
            if (!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid email or password"));
            }

            // Get user details
            User user = userService.findByEmail(loginDTO.getEmail());

            // Generate JWT with userId as the subject
            String token = jwtUtil.generateToken(user.getId());

            // Prepare response
            String dashboardApiUrl = "/api/users/" + user.getId() + "/dashboard";
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "token", token,
                    "dashboardApiUrl", dashboardApiUrl
            ));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred"));
        }
    }
}

