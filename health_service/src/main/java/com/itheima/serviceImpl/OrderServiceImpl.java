package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.AppointmentDao;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.exception.MyException;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/7 10:44
 */
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @Autowired
    private MemberDao memberDao;

    /**
     * @Description: 提交预约
     * @Param: [map]
     * @return: com.itheima.pojo.Order
     */
    @Override
    @Transactional
    public Order submitOrder(Map<String, String> map) throws MyException {
        // 根据日期判断是否可以预约
        String orderDate = map.get("orderDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // orderDate = dateFormat.format(orderDate);
        OrderSetting orderSetting = appointmentDao.findOrderSettingByDate(orderDate);
        if (null == orderSetting) {
            throw new MyException(MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        if (orderSetting.getNumber() <= orderSetting.getReservations()) {
            throw new MyException(MessageConstant.ORDER_FULL);
        }

        // 可预约，则判断是否是会员
        String telephone = map.get("telephone");
        Member member = memberDao.isMember(telephone);
        if (null == member) { // 不是会员
            member = new Member();

            member.setName(map.get("name"));
            member.setSex(map.get("sex"));
            member.setPhoneNumber(telephone);
            member.setIdCard(map.get("idCard"));
            member.setRegTime(new Date());

            memberDao.addMember(member);
        }
        Integer memberId = member.getId();

        // 判断是否预约过,member_id,package_id,orderDate
        Map<String,Object> condition = new HashMap();
        condition.put("memberId",memberId);
        condition.put("orderDate",orderDate);
        condition.put("package_id",map.get("setmealId"));
        Integer orderCount = orderDao.findOrderByCondition(condition);
        if (orderCount > 0) { // 代表已预约
            throw new MyException(MessageConstant.HAS_ORDERED);
        }

        // 未预约则更新数据库
        Order order = new Order();
        order.setMemberId(memberId);
        try {
            order.setOrderDate(dateFormat.parse(orderDate));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MyException("日期转换有误");
        }
        order.setOrderType(MessageConstant.ORDERTYPE_WEIXIN);
        order.setOrderStatus(MessageConstant.ORDERSTATUS_NO);
        order.setPackageId(Integer.valueOf(map.get("setmealId")));
        orderDao.addOrder(order);

        // 更新已预约人数
        appointmentDao.updateReservations(orderDate);

        return order;
    }

    /** 
     * @Description: 预约成功，数据回显
     * @Param: [id] 
     * @return: java.util.Map<java.lang.String,java.lang.Object> 
     */ 
    @Override
    public Map<String, Object> findOrderById(Integer id) {
        return orderDao.findOrderById(id);
    }
}
