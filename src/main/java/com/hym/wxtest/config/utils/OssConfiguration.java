package com.hym.wxtest.config.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 返回 OOSClient实例
 */
// 加载spring容器(应用上下文)
@Configuration
public class OssConfiguration {

    @Value("${oos.endpoint}")
    private String endpoint;
    // 访问id
    @Value("${oos.access_key_id}")
    private String accessKeyId;
    // 访问密钥
    @Value("${oos.access_key_secret}")
    private String accessKeySecret;

    /**
     * 获取OOSClient实例
     * @return
     */
    @Bean(name = "oosClient")
    public OSSClient createOOSClient () {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
