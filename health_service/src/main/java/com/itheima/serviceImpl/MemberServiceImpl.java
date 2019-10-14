package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

/*
 * @author Jayvin
 * @date 2019/10/8 9:32
 */
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    /** 
     * @Description: 判断会员，不是就增加会员
     * @Param: [telephone] 
     * @return: void 
     */ 
    @Override
    public void login(String telephone) {
        Member member = memberDao.isMember(telephone);
        if (null == member) { // 不是会员
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberDao.addMember(member);
        }
    }
}
