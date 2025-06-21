package com.zhtang.miaosha.dao;/*
package com.zhtang.miaosha.dao;

import com.zhtang.miaosha.model.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class ProductDao {
    @Select("SELECT * FROM product")
    List<Product> listProducts();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductById(@Param("id") Long id);

    @Insert("INSERT INTO product(id, name, price, stock, pic) VALUES(#{id},#{name}, #{price}, #{stock}, #{pic})")
    void saveProduct(Product product);

    @Update("UPDATE product SET stock=stock-1 WHERE id = #{id} AND stock > 0")
    void deductProductStock(@Param("id") Long id);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteProduct(@Param("id") Long id);
}
*/
