package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/22 15:49
 */
public interface CheckItemDao {
    @Insert("insert into t_checkitem values (null,#{code},#{name},#{sex},#{age},#{price},#{type},#{remark},#{attention})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false,statement="SELECT last_insert_id()")
    void addItem(CheckItem checkitem);

    Page<CheckItem> findItemByCondition(String queryString);
    // PageInfo<CheckItem> findItemByCondition(String queryString);

    @Select("select count(1) from t_checkgroup_checkitem where checkitem_id=#{id}")
    Integer countItemById(Integer id);

    @Delete("delete from t_checkitem where id=#{id}")
    void deleteItemById(Integer id);

    @Select("select * from t_checkitem where id=#{id}")
    CheckItem findItemById(Integer id);

    @Update("update t_checkitem set code=#{code},name=#{name},sex=#{sex},age=#{age},price=#{price},type=#{type},remark=#{remark},attention=#{attention} where id=#{id}")
    void updateItem(CheckItem checkItem);

    @Select("select * from t_checkitem")
    List<CheckItem> findAll();

}
