package com.zhtang.miaosha.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Orders {
    private Long id;
    private Long productId;
    private BigDecimal amount;
}
