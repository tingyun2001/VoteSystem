package com.example.voting;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface VoteMapper {

    @Select("CALL sp_get_items()")
    List<VoteItem> getItems();

    @Select("CALL sp_add_item(#{name})")
    void addItem(@Param("name") String name);

    @Select("CALL sp_update_item(#{id}, #{name})")
    void updateItem(@Param("id") int id, @Param("name") String name);

    @Select("CALL sp_delete_item(#{id})")
    void deleteItem(@Param("id") int id);

    @Select("CALL sp_vote(#{voter}, #{itemId})")
    void vote(@Param("voter") String voter, @Param("itemId") int itemId);
}
