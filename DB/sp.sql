DELIMITER //

CREATE PROCEDURE sp_AddLikeItem(
    IN p_user_id VARCHAR(20),
    IN p_product_id INT,
    IN p_qty INT,
    IN p_account VARCHAR(50)
)
BEGIN
    DECLARE v_price DECIMAL(15,2);
    DECLARE v_fee_rate DECIMAL(5,4);
    
    SELECT price, fee_rate INTO v_price, v_fee_rate FROM products WHERE id = p_product_id;
    
    INSERT INTO like_list (user_id, product_id, order_qty, account, total_fee, total_amount)
    VALUES (
        p_user_id, 
        p_product_id, 
        p_qty, 
        p_account, 
        v_price * p_qty * v_fee_rate, 
        (v_price * p_qty) + (v_price * p_qty * v_fee_rate)
    );
END //

CREATE PROCEDURE sp_GetLikeList(IN p_user_id VARCHAR(20))
BEGIN
    SELECT 
        l.sn, p.name AS product_name, p.price, p.fee_rate,
        l.order_qty, l.account, l.total_fee, l.total_amount,
        u.email, u.user_name
    FROM like_list l
    JOIN products p ON l.product_id = p.id
    JOIN users u ON l.user_id = u.user_id
    WHERE l.user_id = p_user_id;
END //
-- 刪除
CREATE PROCEDURE sp_DeleteLikeItem(IN p_sn INT)
BEGIN
    DELETE FROM like_list WHERE sn = p_sn;
END //
-- 修改
CREATE PROCEDURE sp_UpdateLikeItem(IN p_sn INT, IN p_qty INT, IN p_account VARCHAR(50))
BEGIN
    UPDATE like_list l
    JOIN products p ON l.product_id = p.id
    SET l.order_qty = p_qty,
        l.account = p_account,
        l.total_fee = p.price * p_qty * p.fee_rate,
        l.total_amount = (p.price * p_qty) + (p.price * p_qty * p.fee_rate)
    WHERE l.sn = p_sn;
END //

DELIMITER ;