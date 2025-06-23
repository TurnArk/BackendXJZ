package com.zhtang.miaosha.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    /**
     * 主键商品ID
     */
    @Id(keyType = KeyType.Generator)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品图片 URL 地址
     */
    private String pic;
}
