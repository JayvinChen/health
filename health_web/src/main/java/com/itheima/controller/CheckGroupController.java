package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/23 12:51
 */
@RequestMapping("/checkGroup")
@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HEALTH_MANAGER')")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * @Description: 新增检查组，用map接收参数，必须用【@RequestBody】
     * @Param: [map]
     * @return: com.itheima.entity.Result
     */
    // @PostMapping("/addGroup")
    // public Result addGroup(@RequestBody Map<String,Object> map) {
    //     // 1. 拿到POJO的map格式
    //     Map<String,Object> formData = (Map<String,Object>)map.get("formData");
    //
    //     // 2. 先把map格式转换成json格式，再转换成POJO，以下两种方法都可
    //     // CheckGroup checkGroup = JSONObject.parseObject(JSONObject.toJSONString(formData), CheckGroup.class);
    //     CheckGroup checkGroup = new Gson().fromJson(JSONObject.toJSONString(formData), CheckGroup.class);
    //
    //     // 3. 直接通过map获得List<Integer>
    //     List<Integer> checkitemIds = (List<Integer>) map.get("checkitemIds");
    //
    //     checkGroupService.addgroup(checkGroup,checkitemIds);
    //     return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    // }

    /** 
     * @Description: 新增检查组，用POJO接收参数，相对简单
     * @Param: [checkitemIds, checkGroup] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/addGroup")
    public Result addGroup(@RequestParam List<Integer> checkitemIds,@RequestBody CheckGroup checkGroup){
        checkGroupService.addgroup(checkGroup,checkitemIds);
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    
    /** 
     * @Description: 分页查询 
     * @Param: [queryPageBean] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }
    
    /** 
     * @Description: 根据id查询检查组
     * @Param: [id] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/{id}")
    public Result findGroupById(@PathVariable("id") Integer id){
        CheckGroup checkGroup = checkGroupService.findGroupById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /** 
     * @Description: 更新检查组
     * @Param: [checkitemIds, checkGroup] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/updateGroup")
    @PreAuthorize("hasAuthority('CHECKGROUP_EDIT')")
    public Result updateGroup(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        checkGroupService.updateGroup(checkitemIds,checkGroup);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /** 
     * @Description: 根据id删除检查组
     * @Param: [id] 
     * @return: com.itheima.entity.Result 
     */ 
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CHECKGROUP_DELETE')")
    public Result deleteGroup(@PathVariable("id") Integer id){
        checkGroupService.deleteGroup(id);
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /** 
     * @Description: 查询所有检查组
     * @Param: [] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> list = checkGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
}
