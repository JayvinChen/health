package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import com.itheima.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * @author Jayvin
 * @date 2019/9/24 21:11
 */
@RestController
@RequestMapping("/package")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HEALTH_MANAGER')")
public class PackageController {
    @Reference
    private PackageService packageService;
    @Autowired
    private JedisPool jedisPool;

    /** 
     * @Description: 上传图片 
     * @Param: [imgFile] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/uploadImg")
    public Result uploadImg(MultipartFile imgFile){
        // 1. 调用业务层上传图片，MultipartFile imgFile是一个文件，dubbo不支持这种参数传输，service拿到参数解码失败
        // Map<String,String> picInfo = packageService.uploadImg(imgFile);
        // return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,picInfo);

        // 2. 在web层上传图片，实测有效
        // 获取要上传的图片的文件名
        String filename = imgFile.getOriginalFilename();
        // 转化成UUID文件名，并保存在数据库
        filename = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
        try {
            // 调用UUID上传图片
            QiNiuUtil.uploadViaByte(imgFile.getBytes(),filename);
            // 获取上传成功后的图片URL，用于图片回显
            String picURL = QiNiuUtil.getPicURL(filename);
            // 封装返回结果
            Map<String,String> picInfo = new HashMap();
            picInfo.put("picURL",picURL);
            picInfo.put("filename",filename);
            // 返回数据
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,picInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 上传失败
        return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
    }

    /** 
     * @Description: 套餐分页查询
     * @Param: [queryPageBean] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Package> pageResult = packageService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }

    /** 
     * @Description: 新增套餐
     * @Param: [checkgroupIds, aPackage] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/addPackage")
    @Transactional
    public Result addPackage(Integer[] checkgroupIds, @RequestBody Package aPackage){
        packageService.addPackage(checkgroupIds,aPackage);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * @Description: 根据id查询套餐,查询结果封装了检查组旧的ids，并不是更新后的ids，编辑时不可以使用！
     * @Param: [id, num]
     * @return: com.itheima.pojo.Package
     */
    @GetMapping("/findPackageById")
    public Result findPackageById(Integer id){
        Package aPackage = packageService.findPackageByIdWithGroupIds(id);
        // 设置img的属性值为图片的url，用于数据回显
        aPackage.setImg(QiNiuUtil.getPicURL(aPackage.getImg()));
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,aPackage);
    }

    /** 
     * @Description: 更新套餐 
     * @Param: [checkgroupIds, aPackage] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/updatePackage")
    public Result updatePackage(Integer[] checkgroupIds, @RequestBody Package aPackage){
        packageService.updatePackage(checkgroupIds,aPackage);
        Jedis jedis = jedisPool.getResource();
        jedis.del("aPackages");
        jedis.del("packageDetail_id="+aPackage.getId());
        jedis.del("package_id="+aPackage.getId());
        return new Result(true,MessageConstant.EDIT_PACKAGE_SUCCESS);
    }
}
