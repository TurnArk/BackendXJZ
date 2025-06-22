package com.zhtang.miaosha.service.impl;

import com.zhtang.miaosha.mapper.ProductMapper;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.service.ProductService;
import com.zhtang.miaosha.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zhtang.miaosha.common.Status
import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    // 查询
    @Override
    public Product getProduct(Long id) throws MyException {
        Product product = productMapper.getProductById(id);
        if (product == null) {
            throw new MyException(Status.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    // 新建商品
    @Override
    @Transactional
    public void createProduct(Product product){

        productMapper.insertProduct(product);
    }

    // 更新商品价格
    @Override
    @Transactional
    public Product updatePrice(Long id, BigDecimal newPrice)  {
        productMapper.updateProductPrice(id, newPrice);
        return null;
    }

    // 删除商品
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productMapper.deleteProductById(id);
    }
}
