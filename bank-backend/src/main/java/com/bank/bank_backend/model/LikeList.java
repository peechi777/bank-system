package com.bank.bank_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "like_list")
@Data
public class LikeList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;          

    @Column(name = "user_id")
    private String userId;    

    @Column(name = "product_id")
    private Long productId;   

    @Column(name = "order_qty")
    private Integer orderQty; 

    private String account;  

    @Column(name = "total_fee")
    private Double totalFee; 

    @Column(name = "total_amount")
    private Double totalAmount; 
}