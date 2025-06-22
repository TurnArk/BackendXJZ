package com.zhtang.miaosha.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Orders {
    private Long id;
    private Long productId;
    private BigDecimal amount;
}
