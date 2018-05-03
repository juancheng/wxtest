package com.hym.wxtest.service.impl;

import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.entity.MailEntity;
import com.hym.wxtest.service.AreaService;
import com.hym.wxtest.utils.JavaMailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceImplTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void listArea() {
        BaseResult<List<Area>> baseResult = areaService.listArea();
        List<Area> data = baseResult.getData();
        assertEquals(14 , data.size());
    }

    @Test
    public void findAreaById() {
//        Area area = areaService.findAreaById(1);
//        assertEquals("北京南", area.getAreaName());
    }

    @Test
    public void saveArea() {
//        Area area = new Area();
//        area.setAreaName("上海");
//        area.setPriority(2);
//        area.setCreateTime(new Date());
//        area.setLastEditTime(new Date());
//        int influenceNum = areaService.saveArea(area);
//        assertEquals(1, influenceNum);
    }

    @Test
    public void updateArea() {
//        Area area = new Area();
//        area.setAreaId(2);
//        area.setAreaName("上海南");
//        area.setPriority(2);
//        area.setLastEditTime(new Date());
//        int influenceNum = areaService.updateArea(area);
//        assertEquals(1, influenceNum);
    }

    @Test
    public void deleteArea() {
//        int influenceNum = areaService.deleteArea(2);
//        assertEquals(1, influenceNum);
    }

    @Test
    public void sendEmail() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setTitle("来自wxtest1的邮件");
        mailEntity.setContent("您好，欢迎加入" + new Date().toString() + "");
        mailEntity.setContentType("text/html;charset=utf-8");
        List<String> toAddress = new ArrayList<>();
        toAddress.add("13695316061@163.com");
        toAddress.add("sd.hz.jc.hym@qq.com");
        mailEntity.setToAddress(toAddress);
        try {
            JavaMailUtil.sendMail(mailEntity);
        } catch(AuthenticationFailedException e){
            e.printStackTrace();
            throw new RuntimeException("请管理员核实密钥...");
        } catch(SendFailedException e){
            e.printStackTrace();
            throw new RuntimeException("请重新核实邮件地址...");
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}