package com.itheima.service;

import com.itheima.pojo.BusinessData;

import java.util.List;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/12 13:47
 */
public interface ReportService {
    Map<String, List<Object>> getMemberCount();

    List<Map<String,String>> getMemberSexProportion();

    List<Map<String,Object>> getPackageCount();

    BusinessData getBusinessDataReport();
}
