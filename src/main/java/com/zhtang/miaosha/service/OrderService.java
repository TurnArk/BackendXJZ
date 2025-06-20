package com.zhtang.miaosha.service;

import com.zhtang.miaosha.model.Order;
import com.zhtang.miaosha.model.Product;
import org.springframework.transaction.annotation.Transactional;
import com.zhtang.miaosha.dao.OrderDao;

public class OrderService {
    @Transactional
    public void createOrder(Order order) {
        OrderDao.saveOrder(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        OrderDao.deleteOrder(id);
    }

    @Transactional
    public void updateOrder(Order order) {
        OrderDao.updateOrder(order);
    }

    @Transactional
    public Order getOrderById(Long id) {
        return OrderDao.getOrderById(id);
    }

    @Transactional
    public void seckill(Long productId){

        //查询商品
        Product product = ProductService.getProductById(productId);

        if(product.getStock() < 0){
            throw new RuntimeException("库存不足");
        }

        //创建订单
        Order order = new Order();
        order.setProductId(productId);
        order.setAmount(product.getPrice());
        OrderDao.saveOrder(order);
    }

    //减库存
    int updateNum = productService.deductProductStock(productId);

    if(updateNum <= 0){
        throw new RuntimeException("商品已售完");
    }
}
