package com.bank.bank_backend.services; 

import com.bank.bank_backend.dto.LikeListDTO;
import com.bank.bank_backend.repository.LikeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// CURD

@Service
public class LikeListService {

    @Autowired
    private LikeListRepository likeListRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    //新增
    @Transactional
    public void addProductToLikeList(String userId, Long productId, Integer qty, String account) {
        //基礎輸入檢查
        if (userId == null || productId == null || qty == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        likeListRepository.addLikeItem(userId, productId.intValue(), qty, account);
    }
    //  LIKELIST
    public List<LikeListDTO> getLikeList(String userId) {
        String sql = "CALL sp_GetLikeList(?)";

        // JdbcTemplate mapping,dto
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            LikeListDTO dto = new LikeListDTO();
            dto.setSn(rs.getLong("sn"));
            dto.setProductName(rs.getString("product_name"));
            dto.setPrice(rs.getDouble("price"));
            dto.setFeeRate(rs.getDouble("fee_rate"));
            dto.setOrderQty(rs.getInt("order_qty")); 
            dto.setAccount(rs.getString("account"));
            dto.setTotalFee(rs.getDouble("total_fee"));
            dto.setTotalAmount(rs.getDouble("total_amount"));
            dto.setEmail(rs.getString("email"));
            dto.setUserName(rs.getString("user_name"));
            return dto;
        }, userId);
    }

    //revise
    @Transactional
    public void updateLikeItem(Long sn, Integer qty, String account) {
        String sql = "CALL sp_UpdateLikeItem(?, ?, ?)";
        jdbcTemplate.update(sql, sn, qty, account);
}

    //delete
    @Transactional
    public void deleteLikeItem(Long sn) {
        String sql = "CALL sp_DeleteLikeItem(?)";
        
        try {
            int rows = jdbcTemplate.update(sql, sn);
            if (rows > 0) {
                System.out.println("成功刪除收藏，序號: " + sn);
            } else {
                System.out.println("找不到該序號，無法刪除: " + sn);
            }
        } catch (Exception e) {
            throw new RuntimeException("資料庫刪除失敗: " + e.getMessage());
        }
    }
}