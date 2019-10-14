package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/27 8:52
 */
public interface AppointmentDao {
    @Select("SELECT count(1) from t_ordersetting where orderDate=#{orderDate}")
    Integer getNumber(String orderDate);

    @Update("update t_ordersetting set number=#{number} where orderDate=#{orderDate}")
    void updateNumber(@Param("orderDate") String orderDate, @Param("number") Integer number);

    @Insert("INSERT into t_ordersetting values (null, #{orderDate}, #{number}, null)")
    void insertNumber(@Param("orderDate") String orderDate, @Param("number") Integer number);

    @Select("SELECT * from t_ordersetting where orderDate between #{month}'-01' and #{month}'-31'")
    List<OrderSetting> getOrderSettings(String month);

    @Select("SELECT * from t_ordersetting where orderDate=#{orderDate}")
    OrderSetting findOrderSettingByDate(String orderDate);

    @Update("UPDATE t_ordersetting set reservations=reservations+1 where orderDate=#{orderDate}")
    void updateReservations(String orderDate);
}
