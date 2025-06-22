package com.zhtang.miaosha.mapper;

import com.zhtang.miaosha.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;

@Mapper
public interface ProductMapper {
    Product getProductById(Long id);
    void insertProduct(Product product);
    void updateProductPrice(Long id, BigDecimal price);
    void deleteProductById(Long id);
}
