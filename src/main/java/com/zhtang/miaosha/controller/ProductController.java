package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.common.Result;
import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.PageResult;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.ResponseMessage;
import com.zhtang.miaosha.pojo.dto.ProductPageQueryDTO;
import com.zhtang.miaosha.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品模块功能
 * 1.查询单个商品
 * 2.查询所有商品
 * 3.新建商品
 * 4.删除商品
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 查询
    @GetMapping("/{id}")
    public Result<Product> getProduct(@RequestBody Product product) throws MyException {
        //log.info("查询{}",Product.getProductId());
        return Result.success(productService.getProduct(product.getId()));
    }

    // 分页查询所有商品
    @GetMapping("/list")
    public Result<PageResult> listProduct(ProductPageQueryDTO productPageQueryDTO) {
        PageResult pageResult = productService.listProduct(productPageQueryDTO);
        return  Result.success(pageResult);
    }

    // 新建商品
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        //todo:
        return null;
    }

    // 更新商品价格
    @PutMapping("/{id}/price")
    public Result<Product> updatePrice(@RequestBody Product product) {
        log.info("更新商品价格--{}", product.getPrice());
        return Result.success(ProductService.updataPrice);
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        //todo:
        return null;
    }
}
