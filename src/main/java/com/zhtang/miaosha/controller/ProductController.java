package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.ResponseMessage;
import com.zhtang.miaosha.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 查询
    @GetMapping("/{id}")
    public ResponseMessage getProduct(@PathVariable("id") Integer id) {
        Product product = productService.getProduct(Long.valueOf(id));
        return ResponseMessage.success(product);
    }


    // 新建商品
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        //todo:
        return null;
    }

    // 更新商品价格
    @PutMapping("/{id}/price")
    public ResponseEntity<String> updatePrice(@PathVariable Long id,
                                              @RequestParam BigDecimal price) {
        //todo:
        return null;
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        //todo:
        return null;
    }
}
