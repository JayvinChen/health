package com.itheima.quartz;

import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.PackageDao;
import com.itheima.dao.ReportDao;
import com.itheima.pojo.BusinessData;
import com.itheima.pojo.HotPackage;
import com.itheima.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author Jayvin
 * @date 2019/10/12 18:56
 */
public class UpdateBusinessReportData {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ReportDao reportDao;

    /** 
     * @Description: 统计运营数据 
     * @Param: [] 
     * @return: void 
     */
    @Transactional
    public void updateBusinessReportData() {
        Calendar cal = Calendar.getInstance();
        // 凌晨查询，日期要减1，表示查询前一天
        cal.add(Calendar.DAY_OF_MONTH,-1);
        Date date = cal.getTime(); // 前一天
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        String reportDate = dayFormat.format(date); // 前一天日期string
        // 本周星期一
        String monday = dayFormat.format(DateUtil.getThisWeekMonday(date));
        // 本月
        String month = monthFormat.format(date);

        // 今天新增会员
        Integer todayNewMember = memberDao.getMemberCountByDate(reportDate);
        // 总会员
        Integer totalMember = memberDao.getMemberCountByMonth(month);
        // 本周新增会员
        Integer thisWeekNewMember = memberDao.getMemberCountBetweenDay(monday,reportDate);
        // 本月新增会员
        Integer thisMonthNewMember = memberDao.getMemberCountBetweenDay(month+"-01",reportDate);
        // 今天预约数
        Integer todayOrderNumber = orderDao.getOrderCountBydate(reportDate);
        // 今天到诊数
        Integer todayVisitsNumber = orderDao.getVisitCountBydate(reportDate);
        // 本周预约数
        Integer thisWeekOrderNumber = orderDao.getOrderCountBetweenDay(monday,reportDate);
        // 本周到诊数
        Integer thisWeekVisitsNumber = orderDao.getVisitCountBetweenDay(monday,reportDate);
        // 本月预约数
        Integer thisMonthOrderNumber = orderDao.getOrderCountBetweenDay(month+"-01",month+"-31");
        // 本月到诊数
        Integer thisMonthVisitsNumber = orderDao.getVisitCountBetweenDay(month+"-01",reportDate);

        BusinessData businessData = new BusinessData(
                todayNewMember, totalMember, thisWeekNewMember, thisMonthNewMember,
                todayOrderNumber, todayVisitsNumber, thisWeekOrderNumber, thisWeekVisitsNumber,
                thisMonthOrderNumber, thisMonthVisitsNumber,date);
        // 添加运营数据
        reportDao.updateBusinessReportData(businessData);
        // 热门套餐, 取前4位
        List<HotPackage> hotPackages = orderDao.getHotPackage();
        // 添加热门套餐至数据库
        for (HotPackage hotPackage : hotPackages) {
            hotPackage.setReportDate(date);
            reportDao.updateHotPackage(hotPackage);
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring_quartz_update.xml");
    }

}
