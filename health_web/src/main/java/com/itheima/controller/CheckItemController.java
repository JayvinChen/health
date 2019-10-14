package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/22 15:42
 */
@RequestMapping("/checkItem")
@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HEALTH_MANAGER')")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    
    /** 
     * @Description: 新增检查项
     * @Param: [checkitem] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkitem){
        checkItemService.addItem(checkitem);
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * @Description: 按条件分页查询
     * @Param: [queryPageBean]
     * @return: com.itheima.entity.Result
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.findItemByCondition(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    /** 
     * @Description: 删除检查项
     * @Param: [id] 
     * @return: com.itheima.entity.Result 
     */ 
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result deleteItemById(@PathVariable("id") Integer id){
        checkItemService.deleteItem(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /** 
     * @Description: 根据id查询检查项
     * @Param: [id] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/findItemById")
    public Result findItemById(Integer id){
        CheckItem checkItem = checkItemService.findItemById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    /** 
     * @Description: 更新检查项
     * @Param: [checkItem] 
     * @return: com.itheima.entity.Result 
     */ 
    // @PostMapping("/updateItem")
    @PutMapping
    public Result updateItem(@RequestBody CheckItem checkItem){
        checkItemService.updateItem(checkItem);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    
    /** 
     * @Description: 查询所有检查项
     * @Param: [] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> list = checkItemService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }
}
