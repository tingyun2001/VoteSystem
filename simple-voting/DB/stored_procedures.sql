USE voting_db;
DELIMITER $$

-- 取得所有項目與票數
DROP PROCEDURE IF EXISTS sp_get_items $$
CREATE PROCEDURE sp_get_items()
BEGIN
    SELECT vi.item_id, vi.item_name, COUNT(vr.record_id) AS vote_count
    FROM vote_item vi
    LEFT JOIN vote_record vr ON vi.item_id = vr.item_id
    GROUP BY vi.item_id, vi.item_name;
END $$

-- 新增項目
DROP PROCEDURE IF EXISTS sp_add_item $$
CREATE PROCEDURE sp_add_item(IN p_name VARCHAR(100))
BEGIN
    INSERT INTO vote_item (item_name) VALUES (p_name);
END $$

-- 更新項目
DROP PROCEDURE IF EXISTS sp_update_item $$
CREATE PROCEDURE sp_update_item(IN p_id INT, IN p_name VARCHAR(100))
BEGIN
    UPDATE vote_item SET item_name = p_name WHERE item_id = p_id;
END $$

-- 刪除項目
DROP PROCEDURE IF EXISTS sp_delete_item $$
CREATE PROCEDURE sp_delete_item(IN p_id INT)
BEGIN
    START TRANSACTION;
        DELETE FROM vote_record WHERE item_id = p_id;
        DELETE FROM vote_item   WHERE item_id = p_id;
    COMMIT;
END $$

-- 投票
DROP PROCEDURE IF EXISTS sp_vote $$
CREATE PROCEDURE sp_vote(IN p_voter VARCHAR(50), IN p_item_id INT)
BEGIN
    INSERT INTO vote_record (voter_name, item_id) VALUES (p_voter, p_item_id);
END $$

DELIMITER ;
