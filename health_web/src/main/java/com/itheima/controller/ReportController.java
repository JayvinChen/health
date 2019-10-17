package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.BusinessData;
import com.itheima.service.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 * @author Jayvin
 * @date 2019/10/11 9:40
 */
@RestController
@RequestMapping("/report")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class ReportController {
    @Reference
    private ReportService reportService;
    
    /** 
     * @Description: 查询会员数量
     * @Param: [] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/getMemberReport")
    public Result getMemberReport(){
        Map<String,List<Object>> map = reportService.getMemberCount();
        return new Result(true,MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }

    /** 
     * @Description: 查询会员男女占比
     * @Param: [] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/getMemberSexProportion")
    public Result getMemberSexProportion(){
        List<Map<String,String>> memberSexProportion = reportService.getMemberSexProportion();
        List<String> sex = new ArrayList();
        if (null != memberSexProportion && memberSexProportion.size() != 0) {
            for (Map<String, String> map : memberSexProportion) {
                sex.add(map.get("name"));
            }
        }
        Map<String,Object> data = new HashMap();
        data.put("memberSexProportion",memberSexProportion);
        data.put("sex",sex);
        return new Result(true,MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,data);
    }
    /**
     * @Description: 查询套餐预约数量占比
     * @Param: []
     * @return: com.itheima.entity.Result
     */
    @GetMapping("/getPackageReport")
    public Result getPackageReport(){
        // 前端要求返回的数据：packageNames->List<String>,packageCount->List<Map<value:..,name:..>>
        List<Map<String,Object>> packageCount = reportService.getPackageCount();
        List<String> packageNames = new ArrayList();
        if (null != packageCount && packageCount.size() != 0) {
            for (Map<String,Object> map : packageCount) {
                packageNames.add((((String) map.get("name"))));
            }
        }
        Map<String,Object> data = new HashMap();
        data.put("packageNames",packageNames);
        data.put("packageCount",packageCount);
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,data);
    }

    /**
     * @Description: 查询运营数据
     * @Param: []
     * @return: com.itheima.entity.Result
     */
    @GetMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        BusinessData data = reportService.getBusinessDataReport();
        return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,data);
    }
}
