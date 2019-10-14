package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.PackageDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author Jayvin
 * @date 2019/9/24 21:07
 *
 * 弹出编辑窗口时，要注意图片的回显要求是url，而从数据库查询得到的是UUID，所以要封装成url并返回
 * 编辑套餐时，要注意前端传过来的img数据！！
 */
@Service(interfaceClass = PackageService.class)
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageDao packageDao;

    /**
     * @Description: 上传图片 !cannot work!
     * @Param: [imgFile]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     */

    /**
     * @Description: 套餐分页查询
     * @Param: [queryPageBean]
     * @return: com.itheima.entity.PageResult<com.itheima.pojo.Package>
     */
    @Override
    public PageResult<Package> findPage(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Package> page = packageDao.findPackage(queryPageBean.getQueryString());

        return new PageResult<Package>(page.getTotal(),page.getResult());
    }

    /** 
     * @Description: 新增套餐 
     * @Param: [checkgroupIds, aPackage] 
     * @return: void
     */ 
    @Override
    @Transactional
    public void addPackage(Integer[] checkgroupIds, Package aPackage) throws MyException {
        packageDao.addPackage(aPackage);

        if (checkgroupIds != null && checkgroupIds.length != 0) {
            for (Integer checkgroupId : checkgroupIds) {
                packageDao.addPackageGroup(aPackage.getId(),checkgroupId);
            }
        }else {
            throw new MyException(MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    /**
     * @Description: 根据id查询套餐，返回值包含检查组ids
     * @Param: [id]
     * @return: com.itheima.pojo.Package
     */ 
    @Override
    public Package findPackageByIdWithGroupIds(Integer id) {
        return packageDao.findPackageById_withCheckGroupIds(id);
    }

    /**
     * @Description: 更新套餐
     * @Param: [checkgroupIds, aPackage]
     * @return: void
     */
    @Override
    @Transactional
    public void updatePackage(Integer[] checkgroupIds, Package aPackage) throws MyException {
        // 如果前端没更改图片，那前端带过来的img是url，不是文件名，不能更新到数据库
        if (aPackage.getImg().startsWith("http:")) {
            //要更新原有的img为imgUUID，才能更新到数据库
            String imgUUID = packageDao.findPackageById(aPackage.getId()).getImg();
            aPackage.setImg(imgUUID);
        }
        // 如果前端更改图片，则带过来的是UUID，可直接更新
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        aPackage.setUpdateTime(dateFormat.format(new Date()));
        packageDao.updatePackage(aPackage);

        packageDao.deletePackageGroup(aPackage.getId());
        if (checkgroupIds != null && checkgroupIds.length != 0) {
            for (Integer checkgroupId : checkgroupIds) {
                packageDao.addPackageGroup(aPackage.getId(),checkgroupId);
            }
        }else {
            throw new MyException(MessageConstant.EDIT_PACKAGE_FAIL);
        }
    }

    /**
     * @Description: 获取所有套餐列表
     * @Param: []
     * @return: java.util.List<com.itheima.pojo.Package>
     */
    @Override
    public List<Package> findAll() {
        return packageDao.findAll();
    }

    /** 
     * @Description: 根据id查询套餐，返回值包含检查组检查项信息
     * @Param: [id] 
     * @return: com.itheima.pojo.Package 
     */ 
    @Override
    public Package findPackageDetailById(Integer id) {
        return packageDao.findPackageById_WithCheckItems(id);
    }

    /** 
     * @Description: 根据id查询套餐，返回值只有套餐信息
     * @Param: [id] 
     * @return: com.itheima.pojo.Package 
     */ 
    @Override
    public Package findPackageById(Integer id) {
        return packageDao.findPackageById(id);
    }
}
