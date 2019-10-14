package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.CheckItem;

import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/22 15:43
 */
public interface CheckItemService {
    void addItem(CheckItem checkitem);

    PageResult findItemByCondition(QueryPageBean queryPageBean);

    void deleteItem(Integer id) throws MyException;

    CheckItem findItemById(Integer id);

    void updateItem(CheckItem checkItem);

    List<CheckItem> findAll();
}
