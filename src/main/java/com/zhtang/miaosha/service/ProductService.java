package com.zhtang.miaosha.service;

import com.zhtang.miaosha.pojo.Product;

import java.math.BigDecimal;

public interface ProductService {
    // 查询
    Product getProduct(Long id);

    // 新建商品
    void createProduct(Product product);

    // 更新商品价格
    void updatePrice(Long id, BigDecimal newPrice);

    // 删除商品
    void deleteProduct(Long id);
}
