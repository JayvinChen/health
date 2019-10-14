package com.itheima.service;

import com.itheima.pojo.OrderSetting;
import java.text.ParseException;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/27 8:47
 */
public interface AppointmentService {
    void uploadAppointment(List<OrderSetting> appointInfo) throws ParseException;

    List<OrderSetting> getOrderSettings(String month);

    void setNumber(String orderDate, Integer number);
}
