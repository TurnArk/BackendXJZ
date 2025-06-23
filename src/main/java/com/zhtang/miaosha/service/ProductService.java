package com.zhtang.miaosha.service;

import com.zhtang.miaosha.pojo.PageResult;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.dto.ProductPageQueryDTO;

import java.math.BigDecimal;

public interface ProductService {
    // 查询
    Product getProduct(Long id) throws MyException;

    // 新建商品
    boolean createProduct(Product product) throws MyException;

    // 更新商品价格
    boolean updatePrice(Long id, BigDecimal newPrice) throws MyException;

    // 删除商品
    boolean deleteProduct(Long id) throws MyException;

    PageResult listProduct(ProductPageQueryDTO productPageQueryDTO);
}
