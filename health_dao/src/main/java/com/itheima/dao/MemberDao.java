package com.itheima.dao;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/7 17:17
 */
public interface MemberDao {
    @Select("select * from t_member where phoneNumber=#{telephone}")
    Member isMember(String telephone);

    @Insert("insert into t_member (name,sex,idCard,phoneNumber,regTime) values (#{name},#{sex},#{idCard},#{phoneNumber},#{regTime})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false,statement="SELECT last_insert_id()")
    void addMember(Member member);

    /**
     * @Description: 至当月最后一天为止的会员数量，如果是最新月份，即会员总数量
     * @Param: [month]
     * @return: java.lang.Integer
     */
    @Select("SELECT count(1) from t_member where regTime <= #{month}'-31'")
    Integer getMemberCountByMonth(String month);

    /** 
     * @Description: 某天的会员数量
     * @Param: [date]
     * @return: java.lang.Integer 
     */ 
    @Select("SELECT count(1) from t_member where regTime = #{date}")
    Integer getMemberCountByDate(String date);

    /** 
     * @Description: 某日期时间段的会员数量
     * @Param: [startDate, endDate] z
     * @return: java.lang.Integer 
     */ 
    @Select("SELECT count(1) from t_member where regTime between #{startDate} and #{endDate}")
    Integer getMemberCountBetweenDay(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("SELECT count(1) value, sex name from t_member GROUP BY sex")
    List<Map<String,String>> getMemberSexProportion();
}
