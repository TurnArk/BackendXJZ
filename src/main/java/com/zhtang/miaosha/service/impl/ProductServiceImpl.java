package com.zhtang.miaosha.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhtang.miaosha.mapper.ProductMapper;
import com.zhtang.miaosha.pojo.PageResult;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.dto.ProductPageQueryDTO;
import com.zhtang.miaosha.service.ProductService;
import com.zhtang.miaosha.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zhtang.miaosha.common.Status;
import java.math.BigDecimal;

@Slf4j
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
    @Transactional
    @Override
    public Product createProduct(Product product) throws MyException {
        int rows = productMapper.insertProduct(product);
        if (rows <= 0) {
            log.info("添加商品失败");
            throw new MyException(Status.PRODUCT_INSERT_ERROR);
        }
        // 返回插入的商品对象
        return product;
    }

    // 更新商品价格
    @Transactional
    @Override
    public boolean updatePrice(Long id, BigDecimal newPrice) throws MyException {
        int updated = productMapper.updateProductPrice(id, newPrice);
        if (updated <= 0) {
            log.info("更新商品价格失败");
            throw new MyException(Status.PRODUCT_NOT_FOUND);
        }
        return true;
    }

    // 删除商品
    @Transactional
    @Override
    public boolean deleteProduct(Long id) throws MyException {
        int deleted = productMapper.deleteProductById(id);
        if (deleted <= 0) {
            log.info("删除商品失败");
            throw new MyException(Status.PRODUCT_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public PageResult listProduct(ProductPageQueryDTO productPageQueryDTO) {
        PageHelper.startPage(productPageQueryDTO.getPage(), productPageQueryDTO.getPageSize());
        Page<Product> page = productMapper.listProduct(productPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
