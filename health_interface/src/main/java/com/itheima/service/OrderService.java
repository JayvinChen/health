package com.itheima.service;

import com.itheima.exception.MyException;
import com.itheima.pojo.Order;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/7 10:41
 */
public interface OrderService {
    Order submitOrder(Map<String, String> map) throws MyException;

    Map<String, Object> findOrderById(Integer id);
}
