package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.AppointmentDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/27 8:50
 */
@Service(interfaceClass = AppointmentService.class)
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;

    /**
     * @Description: 批量设置可预约人数
     * @Param: [orderSettings]
     * @return: void
     */
    @Override
    @Transactional
    public void uploadAppointment(List<OrderSetting> orderSettings) {
        for (OrderSetting orderSetting : orderSettings) {
            String orderDate = orderSetting.getOrderDate().toString();
            Integer number = orderSetting.getNumber();
            if (orderSetting.getOrderDate().compareTo(new Date()) > 0) {
                // 代表该orderDate在当前日期之后，则更新到数据库
                Integer count = appointmentDao.getNumber(orderDate);
                if (count > 0) { // 代表数据库有数据则更新
                    appointmentDao.updateNumber(orderDate,number);
                }else { // 没有则添加
                    appointmentDao.insertNumber(orderDate,number);
                }
            }
        }
    }

    /**
     * @Description: 显示当前月的预约情况
     * @Param: [month]
     * @return: java.util.List<com.itheima.pojo.OrderSetting>
     */
    @Override
    public List<OrderSetting> getOrderSettings(String month) {
        return appointmentDao.getOrderSettings(month);
    }

    /**
     * @Description: 根据日期设置可预约人数
     * @Param: [orderDate, number]
     * @return: void
     */
    @Override
    public void setNumber(String orderDate, Integer number) {
        Integer count = appointmentDao.getNumber(orderDate);
        if (count > 0) {
            appointmentDao.updateNumber(orderDate,number);
        }else {
            appointmentDao.insertNumber(orderDate,number);
        }
    }
}
