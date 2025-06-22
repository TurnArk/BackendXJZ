package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.common.Result;
import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.ResponseMessage;
import com.zhtang.miaosha.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
