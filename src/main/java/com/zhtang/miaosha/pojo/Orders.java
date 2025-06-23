package com.zhtang.miaosha.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mybatisflex.annotation.Table;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "orders")
public class Orders {
    /**
     * 主键订单ID
     */
    @Id(keyType = KeyType.Generator)
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 订单数量
     */
    private BigDecimal amount;
}
