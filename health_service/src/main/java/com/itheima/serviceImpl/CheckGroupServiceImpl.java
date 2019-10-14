package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.MyException;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/23 12:52
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * @Description: 新增检查组，用集合接收ids
     * @Param: [checkGroup, checkitemIds]
     * @return: void
     */
    @Override
    @Transactional
    public void addgroup(CheckGroup checkGroup, List<Integer> checkitemIds) throws MyException {
        checkGroupDao.addgroup(checkGroup);

        if (checkitemIds != null && checkitemIds.size() != 0) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.additems(checkGroup.getId(),checkitemId);
            }
        }else {
            // 如果集合为空，则返回前端页面重新选择检查项
            throw new MyException(MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * @Description: 分页查询
     * @Param: [queryPageBean]
     * @return: com.itheima.entity.PageResult<com.itheima.pojo.CheckGroup>
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            queryString = "%" + queryString + "%";
        }
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);

        return new PageResult<>(page.getTotal(),page.getResult());
    }

    /** 
     * @Description: 根据id查询检查组
     * @Param: [id] 
     * @return: com.itheima.pojo.CheckGroup
     */ 
    @Override
    public CheckGroup findGroupById(Integer id) {
        return checkGroupDao.findGroupById(id);
    }

    /**
     * @Description: 更新检查组，用数组接收ids
     * @Param: [checkitemIds, checkGroup]
     * @return: void
     */
    @Override
    @Transactional
    public void updateGroup(Integer[] checkitemIds, CheckGroup checkGroup) throws MyException {
        // 更新检查组
        checkGroupDao.updateGroup(checkGroup);
        // 删除原有的检查项
        checkGroupDao.deleteItemsById(checkGroup.getId());
        // 添加新的检查项
        if (checkitemIds != null && checkitemIds.length != 0) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.additems(checkGroup.getId(), checkitemId);
            }
        }else {
            throw new MyException(MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    /**
     * @Description: 根据id删除检查组
     * @Param: [id]
     * @return: void
     */
    @Override
    @Transactional
    public void deleteGroup(Integer id) throws MyException {
        // 校验是否被引用
        Integer count = checkGroupDao.countItemsById(id);
        if (count > 0) {
            throw new MyException(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        // 先删除检查项
        checkGroupDao.deleteItemsById(id);
        // 再删除检查组
        checkGroupDao.deleteGroupById(id);
    }

    /** 
     * @Description: 查询所有检查组
     * @Param: [] 
     * @return: java.util.List<com.itheima.pojo.CheckGroup> 
     */ 
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
