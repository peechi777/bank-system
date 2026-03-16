package com.bank.bank_backend.repository;

import com.bank.bank_backend.model.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeListRepository extends JpaRepository<LikeList, Long> {
    
    @Procedure(procedureName = "sp_AddLikeItem")
    void addLikeItem(String p_userId, Integer p_productId, Integer p_qty, String p_account);
}

//Spring Data JPA