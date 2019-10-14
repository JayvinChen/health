package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.*;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/23 12:56
 */
public interface CheckGroupDao {
    @Insert("insert into t_checkgroup values (null,#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false,statement="SELECT last_insert_id()")
    void addgroup(CheckGroup checkGroup);

    /** 
     * @Description: 两个integer参数最好取别名@Param("##")来区分！！
     * @Param: [id, checkitemId] 
     * @return: void 
     */ 
    @Insert("insert into t_checkgroup_checkitem values (#{id},#{checkitemId})")
    void additems(@Param("id") Integer id, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findPage(String queryString);

    CheckGroup findGroupById(Integer id);

    @Update("update t_checkgroup set code=#{code},name=#{name},helpCode=#{helpCode},sex=#{sex},remark=#{remark},attention=#{attention} where id=#{id}")
    void updateGroup(CheckGroup checkGroup);

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
    void deleteItemsById(Integer id);

    @Select("select count(1) from t_package_checkgroup where checkgroup_id=#{id}")
    Integer countItemsById(Integer id);

    @Delete("delete from t_checkgroup where id=#{id}")
    void deleteGroupById(Integer id);

    @Select("select * from t_checkgroup")
    List<CheckGroup> findAll();
}
