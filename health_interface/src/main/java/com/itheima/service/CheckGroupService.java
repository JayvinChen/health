package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/23 12:53
 */
public interface CheckGroupService {
    void addgroup(CheckGroup checkGroup, List<Integer> checkitemIds) throws MyException;

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findGroupById(Integer id);

    void updateGroup(Integer[] checkitemIds, CheckGroup checkGroup) throws MyException;

    void deleteGroup(Integer id) throws MyException;

    List<CheckGroup> findAll();
}
