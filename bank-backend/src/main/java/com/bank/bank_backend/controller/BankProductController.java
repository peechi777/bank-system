package com.bank.bank_backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.web.bind.annotation.*;
import com.bank.bank_backend.model.BankProduct;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class BankProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    @GetMapping
    public List<BankProduct> getAllProducts() {
        String sql = "SELECT id, name, category, price, fee_rate AS feeRate, description FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BankProduct.class));
    }

}