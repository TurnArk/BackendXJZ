package com.zhtang.miaosha.mapper;

import com.github.pagehelper.Page;
import com.zhtang.miaosha.pojo.Product;
import com.zhtang.miaosha.pojo.dto.ProductPageQueryDTO;
import com.zhtang.miaosha.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;

@Mapper
public interface ProductMapper {
    Product getProductById(Long id);
    void insertProduct(Product product);
    void updateProductPrice(Long id, BigDecimal price);
    void deleteProductById(Long id);
    Page<ProductVO> listProduct(ProductPageQueryDTO productPageQueryDTO);
}
