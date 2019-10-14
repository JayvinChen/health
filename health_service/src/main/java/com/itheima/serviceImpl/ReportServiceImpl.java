package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.PackageDao;
import com.itheima.dao.ReportDao;
import com.itheima.pojo.BusinessData;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author Jayvin
 * @date 2019/10/12 13:49
 */
@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PackageDao packageDao;
    @Autowired
    private ReportDao reportDao;

    /**
     * @Description: 查询过去一年的会员数量
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.util.List<java.lang.Object>>
     */
    @Override
    public Map<String, List<Object>> getMemberCount() {
        Map<String, List<Object>> map = new HashMap();
        // 前端要求返回两个集合名称->months,memberCount
        List<Object> months = new ArrayList();
        List<Object> memberCount = new ArrayList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        // 获取当前时间
        Calendar cal = Calendar.getInstance(); //2019-10
        // 从12个月之前开始查
        cal.add(Calendar.MONTH,-12);
        for (int i = 0; i < 12; i++) {
            // 应该查询当前日期的实际情况 2018-11~2019-10
            cal.add(Calendar.MONTH,1);
            String month = dateFormat.format(cal.getTime());
            Integer count = memberDao.getMemberCountByMonth(month);
            months.add(month);
            memberCount.add(count);
            //不查当月的，则放在循环的最后，查询 2018-10~2019-09
            // cal.add(Calendar.MONTH,1);
        }

        map.put("months", months);
        map.put("memberCount", memberCount);
        return map;
    }

    /**
     * @Description: 查询会员男女占比
     * @Param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Override
    public List<Map<String, String>> getMemberSexProportion() {
        List<Map<String, String>> memberSexProportion = memberDao.getMemberSexProportion();
        for (Map<String, String> map : memberSexProportion) {
            if ("1".equals(map.get("name"))) {
                map.put("name","男");
            }
            if ("2".equals(map.get("name"))) {
                map.put("name","女");
            }
        }
        return memberSexProportion;
    }

    /**
     * @Description: 查询套餐预约数量
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.util.List<java.lang.Object>>
     */
    @Override
    public List<Map<String, Object>> getPackageCount() {
        return packageDao.getPackageCount();
    }

    /** 
     * @Description: 查询运营数据
     * @Param: [] 
     * @return: java.util.Map<java.lang.String,java.lang.Object> 
     */ 
    @Override
    public BusinessData getBusinessDataReport() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-1);
        Date date = cal.getTime(); // 前一天日期
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reportDate = dayFormat.format(date); // 前一天日期string

        return reportDao.getBusinessDataReport(reportDate);
    }
}












