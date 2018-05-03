package com.hym.wxtest.config.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Session;
import java.util.Properties;

/**
 * 获取邮箱的配置会话session
 */
@Configuration
public class JavaMailSessionConfiguration {

    @Value("${mail.protocol_key}")
    private String mailProtocolKey;
    @Value("${mail.protocol_value}")
    private String mailProtocolValue;
    @Value("${mail.host_key}")
    private String mailHostKey;
    @Value("${mail.host_value}")
    private String mailHostValue;
    @Value("${mail.starttls_enable_key}")
    private String mailStarttlsEnableKey;
    @Value("${mail.starttls_enable_value}")
    private String mailStarttlsEnableValue;
    @Value("${mail.auth_key}")
    private String mailAuthKey;
    @Value("${mail.auth_value}")
    private String mailAuthValue;
    @Value("${mail.debug_key}")
    private String mailDebugKey;
    @Value("${mail.debug_value}")
    private String mailDebugValue;

    @Bean(name = "javaMailSession")
    public Session createJavaMailSession () {
        Properties properties = new Properties();
        properties.setProperty(mailProtocolKey, mailProtocolValue);
        properties.setProperty(mailHostKey, mailHostValue);
        properties.setProperty(mailStarttlsEnableKey, mailStarttlsEnableValue);
        properties.setProperty(mailAuthKey, mailAuthValue);
        properties.setProperty(mailDebugKey, mailDebugValue);
        return Session.getInstance(properties);
    }
}
