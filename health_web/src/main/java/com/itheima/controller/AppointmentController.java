package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.AppointmentService;
import com.itheima.utils.POIUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author Jayvin
 * @date 2019/9/27 8:46
 */
@RequestMapping("/setAppointment")
@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HEALTH_MANAGER')")
public class AppointmentController {
    @Reference
    private AppointmentService appointmentService;
    
    /** 
     * @Description: 批量设置可预约人数
     * @Param: [excelFile] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/uploadExcelFile")
    public Result uploadAppointSettingFile(MultipartFile excelFile) throws Exception {
        // 读取上传文件中的内容
        List<String[]> excelFileInfo = POIUtil.readExcel(excelFile);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        // 新建OrderSetting集合
        List<OrderSetting> orderSettings = new ArrayList();
        if (excelFileInfo != null && excelFileInfo.size() != 0) {
            //把文件内容转换成OrderSetting对象
            for (String[] info : excelFileInfo) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(format.parse(info[0]));
                orderSetting.setNumber(Integer.valueOf(info[1]));
                orderSettings.add(orderSetting);
            }
            // 调用业务层方法保存到数据库
            appointmentService.uploadAppointment(orderSettings);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        }else {
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
    
    /** 
     * @Description: 显示当前月的预约情况
     * @Param: [month] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/getOrderSettings")
    public Result getOrderSettings(String month){
        List<OrderSetting> orderSettings = appointmentService.getOrderSettings(month);
        // 返回给前端的数据集合
        List<Map<String,Object>> leftobj = new ArrayList();

        if (orderSettings != null && orderSettings.size() != 0) {
            // 新建日期格式，只取天数
            SimpleDateFormat date = new SimpleDateFormat("d");
            for (OrderSetting orderSetting : orderSettings) {
                // 返回的数据格式：{ date: 1, number: 120, reservations: 1 }
                Map<String,Object> obj = new HashMap();
                obj.put("date",date.format(orderSetting.getOrderDate()));
                obj.put("number",orderSetting.getNumber());
                obj.put("reservations",orderSetting.getReservations());

                leftobj.add(obj);
            }
        }
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,leftobj);
    }

    /**
     * @Description: 根据日期设置可预约人数
     * @Param: [number, day]
     * @return: com.itheima.entity.Result
     */ 
    @PostMapping("/setNumber")
    public Result setNumber(Integer number, @RequestBody Date day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = format.format(day);
        appointmentService.setNumber(orderDate,number);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS);
    }
}
