package com.bank.bank_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bank_backend.model.BankProduct;

public interface BankProductRepository extends JpaRepository<BankProduct, Long> {
}


//要用Stored Procedure,add