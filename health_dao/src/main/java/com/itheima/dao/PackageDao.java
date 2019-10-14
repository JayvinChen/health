package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Package;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/9/24 21:06
 */
public interface PackageDao {
    Page<Package> findPackage(String queryString);

    @Insert("insert into t_package values (null,#{name},#{code},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false,statement="SELECT last_insert_id()")
    void addPackage(Package aPackage);

    @Insert("insert into t_package_checkgroup values (#{id},#{checkgroupId})")
    void addPackageGroup(@Param("id") Integer id, @Param("checkgroupId") Integer checkgroupId);

    /** 
     * @Description: 该返回值包含检查组ids
     * @Param: [id] 
     * @return: com.itheima.pojo.Package 
     */ 
    Package findPackageById_withCheckGroupIds(Integer id);

    @Update("update t_package set name=#{name},code=#{code},helpCode=#{helpCode},sex=#{sex},age=#{age},price=#{price},remark=#{remark},attention=#{attention},img=#{img},updateTime=#{updateTime} where id=#{id}")
    void updatePackage(Package aPackage);

    @Delete("delete from t_package_checkgroup where package_id=#{id}")
    void deletePackageGroup(Integer id);
    
    /** 
     * @Description: 根据上次更新时间 查询数据库图片
     * @Param: [lastUpdateTime] 
     * @return: java.util.List<java.lang.String> 
     */ 
    @Select("SELECT img FROM t_package where updateTime >= #{lastUpdateTime}")
    List<String> getImgnames(Date lastUpdateTime);

    @Select("select * from t_package")
    List<Package> findAll();

    /** 
     * @Description: 该返回值包含检查组检查项信息
     * @Param: [id] 
     * @return: com.itheima.pojo.Package 
     */ 
    Package findPackageById_WithCheckItems(Integer id);

    /** 
     * @Description: 该返回值只包含套餐信息 
     * @Param: [id] 
     * @return: com.itheima.pojo.Package 
     */
    @Select("select * from t_package where id=#{id}")
    Package findPackageById(Integer id);

    // 查询套餐预约数量
    @Select("SELECT count(1) value,p.`name` " +
            "from t_order o,t_package p where o.package_id=p.id " +
            "GROUP BY o.package_id")
    List<Map<String, Object>> getPackageCount();

    // 以下2个方法为七牛云删除垃圾图片服务
    // 1. 查询上次删除图片的时间
    @Select("SELECT max(updateTime) from t_removeimgtime")
    Date getUpdateTime();

    // 2. 添加本次更新时间
    @Insert("insert into t_removeimgtime values (null,#{updateTime})")
    void updateTime(String updateTime);
}
