package com.bank.bank_backend.dto;

import lombok.Data;

@Data 
public class LikeListDTO {
    private Long sn;             
    private String productName;  
    private Double price;        
    private Double feeRate;      
    private Integer orderQty;    
    private String account;      
    private Double totalFee;     
    private Double totalAmount;  
    private String email;        
    private String userName;     
}