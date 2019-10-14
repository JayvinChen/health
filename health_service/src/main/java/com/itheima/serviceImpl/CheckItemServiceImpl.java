package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/22 15:49
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /** 
     * @Description: 新增检查项
     * @Param: [checkitem] 
     * @return: void 
     */ 
    @Override
    public void addItem(CheckItem checkitem) {
        checkItemDao.addItem(checkitem);
    }

    /** 
     * @Description: 条件查询
     * @Param: [queryPageBean] 
     * @return: com.itheima.entity.PageResult 
     */ 
    @Override
    public PageResult findItemByCondition(QueryPageBean queryPageBean) {
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            // 不为空，代表有条件查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() +"%");
        }
        // 调用分页插件进行分页查询
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        // 紧跟的方法默认进行分页查询，返回值就是对PageResult的封装
        Page<CheckItem> page = checkItemDao.findItemByCondition(queryPageBean.getQueryString());
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getResult());

        // PageInfo<CheckItem> page = checkItemDao.findItemByCondition(queryPageBean.getQueryString());
        // PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getList());

        return pageResult;
    }

    /** 
     * @Description: 删除检查项
     * @Param: [id] 
     * @return: void 
     */ 
    @Override
    public void deleteItem(Integer id) throws MyException {
        Integer count = checkItemDao.countItemById(id);
        if (count > 0) {
            // 代表被引用，不能删除
            throw new MyException(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }

        checkItemDao.deleteItemById(id);
    }

    /** 
     * @Description: 根据id查询检查项
     * @Param: [id] 
     * @return: com.itheima.pojo.CheckItem 
     */ 
    @Override
    public CheckItem findItemById(Integer id) {
        return checkItemDao.findItemById(id);
    }

    /** 
     * @Description: 更新检查项 
     * @Param: [checkItem] 
     * @return: void
     */ 
    @Override
    public void updateItem(CheckItem checkItem) {
        checkItemDao.updateItem(checkItem);
    }

    /**
     * @Description: 查询所有检查项
     * @Param: []
     * @return: java.util.List<com.itheima.pojo.CheckItem>
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
