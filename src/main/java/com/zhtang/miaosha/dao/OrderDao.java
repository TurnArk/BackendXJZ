package com.zhtang.miaosha.dao;/*
package com.zhtang.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class OrderDao {
    @Select("SELECT * FROM seckillOrder")
    List<Order> listOrders();

    @Select("SELECT * FROM seckillOrder WHERE id = #{id}")
    Order getOrderById(@Param("id") long id);

    @Insert("INSERT INTO seckillOrder(id, prouctId, amount) VALUES(#{id}, #{productId}, #{amount}")
    void saveOrder(Order order);

    @Update("UPDATE seckillOrder SET productId = #{productId}, amount = #{amount} WHERE id = #{id}")
    void updateOrder(Order order);

    @Delete("DELETE FROM seckillOrder WHERE id = #{id}")
    void deleteOrder(@Param("id") long id);
}
*/
