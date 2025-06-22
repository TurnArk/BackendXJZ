package com.zhtang.miaosha.service.impl;

import com.zhtang.miaosha.mapper.ProductMapper;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.service.ProductService;
import com.zhtang.miaosha.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    // 查询
    @Override
    public Product getProduct(Long id) {
        Product product = productMapper.getProductById(id);
        if (product == null) {
            MyException.throwError("商品不存在", 404);
        }
        return product;
    }

    // 新建商品
    @Override
    @Transactional
    public void createProduct(Product product) {
        productMapper.insertProduct(product);
    }

    // 更新商品价格
    @Override
    @Transactional
    public void updatePrice(Long id, BigDecimal newPrice) {
        productMapper.updateProductPrice(id, newPrice);
    }

    // 删除商品
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productMapper.deleteProductById(id);
    }
}
