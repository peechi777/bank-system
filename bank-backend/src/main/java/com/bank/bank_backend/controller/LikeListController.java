package com.bank.bank_backend.controller;

import com.bank.bank_backend.dto.LikeListDTO; 
import com.bank.bank_backend.services.LikeListService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RESTful
@RestController
@RequestMapping("/api/like-list")
//連線
@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET, 
    RequestMethod.POST, 
    RequestMethod.PUT, 
    RequestMethod.DELETE, 
    RequestMethod.OPTIONS
})
public class LikeListController {

    @Autowired
    private LikeListService likeListService;

    @PostMapping("/add")
    public String addToLikeList(
            @RequestParam String userId,
            @RequestParam Long productId,
            @RequestParam Integer qty,
            @RequestParam String account) {
        
        likeListService.addProductToLikeList(userId, productId, qty, account);
        return "Success";
    }

    @GetMapping("/{userId}")
    public List<LikeListDTO> getLikeList(@PathVariable String userId) {
        return likeListService.getLikeList(userId);
    }
    @DeleteMapping("/{sn}")
    public String deleteFavorite(@PathVariable Long sn) {
        likeListService.deleteLikeItem(sn);
        return "Delete Success";
    }
    @PutMapping("/{sn}")
    public ResponseEntity<String> updateFavorite(
            @PathVariable Long sn, 
            @RequestParam Integer qty, 
            @RequestParam String account) {
        try {
            likeListService.updateLikeItem(sn, qty, account);
            return ResponseEntity.ok("Update Success");
    }   catch (Exception e) {
            return ResponseEntity.status(500).body("Update Failed: " + e.getMessage());
    }
}
}