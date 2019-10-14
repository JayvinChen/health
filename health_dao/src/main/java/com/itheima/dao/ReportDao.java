package com.itheima.dao;

import com.itheima.pojo.BusinessData;
import com.itheima.pojo.HotPackage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/12 19:11
 */
public interface ReportDao {
    @Insert("insert into t_businessreport values (null,#{todayNewMember},#{totalMember},#{thisWeekNewMember},#{thisMonthNewMember},#{todayOrderNumber},#{todayVisitsNumber},#{thisWeekOrderNumber},#{thisWeekVisitsNumber},#{thisMonthOrderNumber},#{thisMonthVisitsNumber},#{reportDate})")
    void updateBusinessReportData(BusinessData businessData);

    @Insert("insert into t_hotpackage values (null,#{name},#{count},#{proportion},#{remark},#{reportDate})")
    void updateHotPackage(HotPackage hotPackage);

    BusinessData getBusinessDataReport(String reportDate);
}

