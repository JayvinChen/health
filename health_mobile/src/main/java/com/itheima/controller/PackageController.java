package com.itheima.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import com.itheima.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/27 21:10
 */
@RestController
@RequestMapping("/package")
public class PackageController {
    @Reference
    private PackageService packageService;
    @Autowired
    private JedisPool jedisPool;

    /** 
     * @Description: 获取所有套餐列表,package.html
     * @Param: []
     * @return: com.itheima.entity.Result
     */ 
    @PostMapping("/getPackages")
    public Result getPackages(){
        Jedis jedis = jedisPool.getResource();
        String aPackagesJsonString = jedis.get("aPackages");
        Gson gson = new Gson();
        // redis有缓存，则从缓存中查询
        if (!StringUtils.isEmpty(aPackagesJsonString)) {
            List<Package> aPackages = gson.fromJson(aPackagesJsonString, new TypeToken<List<Package>>() {}.getType());
            return new Result(true,MessageConstant.QUERY_SETMEALLIST_SUCCESS,aPackages);
        }
        // redis没有缓存，则从数据库中查询
        List<Package> aPackages = packageService.findAll();
        if (aPackages != null && aPackages.size() != 0) {
            for (Package aPackage : aPackages) {
                aPackage.setImg(QiNiuUtil.getPicURL(aPackage.getImg()));
            }
        }
        // 保存至Redis
        aPackagesJsonString = gson.toJson(aPackages);
        jedis.set("aPackages",aPackagesJsonString);
        return new Result(true,MessageConstant.QUERY_SETMEALLIST_SUCCESS,aPackages);
    }

    /**
     * @Description: 根据id查询套餐，返回值包含检查组检查项信息,package_detail.html
     * @Param: [id]
     * @return: com.itheima.pojo.Package
     */
    @PostMapping("/findPackageDetailById")
    public Result findPackageDetailById(Integer id){
        Jedis jedis = jedisPool.getResource();
        String packageDetailJsonString = jedis.get("packageDetail_id="+id);
        Gson gson = new Gson();
        // redis有缓存，则从缓存中查询
        if (!StringUtils.isEmpty(packageDetailJsonString)) {
            Package aPackage = gson.fromJson(packageDetailJsonString, Package.class);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,aPackage);
        }
        // redis没有缓存，则从数据库中查询
        Package aPackage = packageService.findPackageDetailById(id);
        // 设置img的属性值为图片的url，用于数据回显
        aPackage.setImg(QiNiuUtil.getPicURL(aPackage.getImg()));
        // 保存至Redis
        packageDetailJsonString = gson.toJson(aPackage);
        jedis.set("packageDetail_id="+id,packageDetailJsonString);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,aPackage);
    }

    /**
     * @Description: 根据id查询套餐，返回值只有套餐信息,orderInfo.html
     * @Param: [id]
     * @return: com.itheima.pojo.Package
     */
    @PostMapping("/findPackageById")
    public Result findPackageById(Integer id){
        Jedis jedis = jedisPool.getResource();
        String packageDetailJsonString = jedis.get("package_id="+id);
        Gson gson = new Gson();
        // redis有缓存，则从缓存中查询
        if (!StringUtils.isEmpty(packageDetailJsonString)) {
            Package aPackage = gson.fromJson(packageDetailJsonString, Package.class);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,aPackage);
        }
        // redis没有缓存，则从数据库中查询
        Package aPackage = packageService.findPackageById(id);
        // 设置img的属性值为图片的url，用于数据回显
        aPackage.setImg(QiNiuUtil.getPicURL(aPackage.getImg()));
        // 保存至Redis
        packageDetailJsonString = gson.toJson(aPackage);
        jedis.set("package_id="+id,packageDetailJsonString);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,aPackage);
    }
}
