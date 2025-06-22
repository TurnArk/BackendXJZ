package com.zhtang.miaosha.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String pic;
}
