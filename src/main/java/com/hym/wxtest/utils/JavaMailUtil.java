package com.hym.wxtest.utils;

import com.hym.wxtest.entity.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送邮件的工具类
 */
@Configuration
public class JavaMailUtil {

    private static Session javaMailSession;

    // 邮件地址
    private static String mailUser;
    // 密码
    private static String mailPassword;
    // 用户昵称
    private static String mailFromNickname;

    @Autowired
    @Qualifier(value = "javaMailSession")
    public void setJavaMailSession(Session javaMailSession) {
        this.javaMailSession = javaMailSession;
    }

    @Value("${mail.user}")
    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }
    @Value("${mail.password}")
    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }
    @Value("${mail.from_nickname}")
    public void setMailFromNickname(String mailFromNickname) {
        this.mailFromNickname = mailFromNickname;
    }

    /**
     * 发送邮件
     * @param mailEntity
     */
    public static void sendMail(MailEntity mailEntity) throws AuthenticationFailedException, SendFailedException, Exception {
        // 创建邮件消息
        Message msg = new MimeMessage(javaMailSession);
        // 设置发件人
        msg.setFrom(new InternetAddress(mailUser, mailFromNickname + " <" + mailUser + ">", "UTF-8"));
        // 设置邮件标题
        msg.setSubject(mailEntity.getTitle());
        // 以html发送邮件
        if (mailEntity.getContentType() != null
                && mailEntity.getContentType().trim().equals("text/html;charset=utf-8")) {
            msg.setContent(mailEntity.getContent(), mailEntity.getContentType().trim());
        } else {
            // 以文本发送邮件
            msg.setText(mailEntity.getContent());
        }
        // 设置发送邮件地址
        List<Address> addresses = new ArrayList<>();
        for (String toAddress : mailEntity.getToAddress()) {
            addresses.add(new InternetAddress(toAddress));
        }
        Address[] arrAddresses = new InternetAddress[addresses.size()];
        addresses.toArray(arrAddresses);
        msg.setRecipients(Message.RecipientType.TO, arrAddresses);
        // 获取发送邮件传输通道
        Transport trans = javaMailSession.getTransport();
        // 校验信息
        trans.connect(mailUser, mailPassword);
        Address[] allRecipients = msg.getAllRecipients();
        // 发送邮件信息
        trans.sendMessage(msg, allRecipients);
        // 关闭发送通道
        trans.close();
    }

}
