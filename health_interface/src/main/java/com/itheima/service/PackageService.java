package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.Package;

import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/9/24 21:06
 */
public interface PackageService {
    PageResult<Package> findPage(QueryPageBean queryPageBean);

    void addPackage(Integer[] checkgroupIds, Package aPackage) throws MyException;

    Package findPackageByIdWithGroupIds(Integer id);

    void updatePackage(Integer[] checkgroupIds, Package aPackage) throws MyException;

    List<Package> findAll();

    Package findPackageDetailById(Integer id);

    Package findPackageById(Integer id);
}
