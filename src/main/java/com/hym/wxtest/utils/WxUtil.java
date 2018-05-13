package com.hym.wxtest.utils;

import com.alibaba.fastjson.JSONObject;
import com.hym.wxtest.dto.WxTemplateMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * 微信相关操作的工具类
 */
public class WxUtil {
    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);
    /**
     * 获取微信access_token
     * @param url
     * @return
     */
    public static String getWeiXinAccessToken(String url) {
        logger.info("url={}", url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            logger.info("sessionData={}", sessionData);
            return sessionData;
        }
        return null;
    }

    // 发送模版消息
    public static String sendTemplateMessage(String url, WxTemplateMessageDto wxTemplateMessageDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<String>(JSONObject.toJSONString(wxTemplateMessageDto), headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            logger.info("sessionData={}", sessionData);
            return sessionData;
        }
        return null;
    }
}
