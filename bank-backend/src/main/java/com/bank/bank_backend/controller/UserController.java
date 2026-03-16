package com.bank.bank_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
 //dbcTemplate 參數化查詢,防止 SQL Injection
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable String userId) {
        String sql = "SELECT user_id AS userId, user_name AS userName, email, account FROM users WHERE user_id = ?";

        return jdbcTemplate.queryForMap(sql, userId);
    }
}