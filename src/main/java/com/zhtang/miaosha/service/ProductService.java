package com.zhtang.miaosha.service;

import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.common.exception.MyException;
import java.math.BigDecimal;

public interface ProductService {
    // 查询
    Product getProduct(Long id) throws MyException;

    // 新建商品
    Product createProduct(Product product);

    // 更新商品价格
    Product updatePrice(Long id, BigDecimal newPrice);

    // 删除商品
    Product deleteProduct(Long id);
}
