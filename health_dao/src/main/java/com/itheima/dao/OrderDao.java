package com.itheima.dao;

import com.itheima.pojo.HotPackage;
import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/7 10:45
 */
public interface OrderDao {
    @Select("select count(*) from t_order where member_id=#{memberId} and orderDate=#{orderDate} and package_id=#{package_id}")
    Integer findOrderByCondition(Map<String, Object> condition);

    @Insert("insert into t_order values (null,#{memberId},#{orderDate},#{orderType},#{orderStatus},#{packageId})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false,statement="SELECT last_insert_id()")
    void addOrder(Order order);

    @Select("SELECT m.name member,m.id mid,p.name setmeal,p.id pid,o.id oid,o.orderDate,o.orderType from t_order o,t_member m,t_package p where m.id=o.member_id and p.id=o.package_id and o.id=#{id}")
    Map<String, Object> findOrderById(Integer id);

    /**
     * @Description: 某天的预约数量
     * @Param: [date]
     * @return: java.lang.Integer
     */
    @Select("SELECT count(1) from t_order where orderDate=#{reportDate}")
    Integer getOrderCountBydate(String date);

    /** 
     * @Description: 某天的已到诊数量 
     * @Param: [date] 
     * @return: java.lang.Integer 
     */ 
    @Select("SELECT count(1) from t_order where orderStatus='已到诊' and orderDate=#{reportDate}")
    Integer getVisitCountBydate(String date);

    /**
     * @Description: 某日期时间段的预约数量
     * @Param: [startDate, endDate]
     * @return: java.lang.Integer
     */
    @Select("SELECT count(1) from t_order where orderDate between #{startDate} and #{endDate}")
    Integer getOrderCountBetweenDay(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * @Description: 某日期时间段的已到诊数量
     * @Param: [startDate, endDate]
     * @return: java.lang.Integer
     */
    @Select("SELECT count(1) from t_order where orderStatus='已到诊' and orderDate between #{startDate} and #{endDate}")
    Integer getVisitCountBetweenDay(@Param("startDate") String startDate, @Param("endDate") String endDate);

    // 热门套餐, 取前4位
    @Select("SELECT p.`name`,count(1) count,count(1)/t.total proportion,p.remark " +
            "from t_order o,t_package p,(SELECT count(1) total from t_order) t where o.package_id=p.id " +
            "GROUP BY o.package_id order by count desc limit 0,4")
    List<HotPackage> getHotPackage();
}