package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.exception.MyException;
import com.zhtang.miaosha.pojo.Product;
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
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProduct(id);
            return ResponseEntity.ok(product);
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 新建商品
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            productService.createProduct(product);
            return ResponseEntity.ok("创建成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 更新商品价格
    @PutMapping("/{id}/price")
    public ResponseEntity<String> updatePrice(@PathVariable Long id,
                                              @RequestParam BigDecimal price) {
        try {
            productService.updatePrice(id, price);
            return ResponseEntity.ok("价格更新成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("删除成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }
}
