package com.zhtang.miaosha.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPageQueryDTO {

    private int page;

    private int pageSize;

    private String name;

}
