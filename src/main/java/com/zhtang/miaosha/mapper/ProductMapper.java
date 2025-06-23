package com.zhtang.miaosha.mapper;

import com.github.pagehelper.Page;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.dto.ProductPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;

/**
 * 映射层
 * @author ZHTang
 */
@Mapper
public interface ProductMapper {
    Product getProductById(Long id);
    int insertProduct(Product product);
    int updateProductPrice(Long id, BigDecimal price);
    int deleteProductById(Long id);
    Page<Product> listProduct(ProductPageQueryDTO productPageQueryDTO);
}
