package com.hym.wxtest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.dto.WxResult;
import com.hym.wxtest.dto.WxTemplateMessageDto;
import com.hym.wxtest.enums.ExpireTimeEnum;
import com.hym.wxtest.enums.WxEnum;
import com.hym.wxtest.exception.WxException;
import com.hym.wxtest.service.WxService;
import com.hym.wxtest.utils.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 微信操作相关的业务逻辑层
 */
@Service
public class WxServiceImpl implements WxService {
    private static final Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);

    @Value("${wx.access_token_url}")
    private String accessTokenUrl;

    @Value("${wx.template_send_url}")
    private String templateSendUrl;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate<String, JSONObject> redisTemplate;

    @Override
    public BaseResult getWxAccessToken() throws WxException {
        logger.info("redisTemplate={}", redisTemplate);
        String key = "wx_access_token";
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) { // 缓存存在
            JSONObject jsonObject = redisTemplate.opsForValue().get(key);
            logger.info("from redis cache={}", jsonObject);
            return new WxResult(WxEnum.ACCESS_TOKEN_SUCCESS, jsonObject);
        }
        String responseData = WxUtil.getWeiXinAccessToken(accessTokenUrl);
        // String responseData = "{\"access_token\":\"9_lcuoP9KZ9OKxyMjMwui4jrA8o-K33Gke2Yt5d4o6Wv2X5rSiskUe1r8nJfjUn2-Oz1VKcVf920umJXUUI_k9_hkJP128rfAlptDo099CG_Skru_KC9hBqwldNSC4rkK9zPkYZjki0IKnxJ1NDBSeAAAUSJ\",\"expires_in\":7200}";
        if (responseData == null) { // 请求数据失败
            throw new WxException(WxEnum.ACCESS_TOKEN_FAIL);
        }
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        String accessToken = jsonObject.getString("access_token");
        if (accessToken == null) { // 获取失败
            Integer errCode = jsonObject.getInteger("errcode");
            String errMsg = jsonObject.getString("errmsg");
            throw new WxException(errCode, errMsg);
        }
        // access_token 获取成功
        redisTemplate.opsForValue().set(key, jsonObject, ExpireTimeEnum.WX_ACCESS_TOKEN.getTime(), ExpireTimeEnum.WX_ACCESS_TOKEN.getTimeUnit());
        logger.info("save redis cache={}", jsonObject);
        return new WxResult(WxEnum.ACCESS_TOKEN_SUCCESS, jsonObject);
    }

    /**
     * 发送模版消息
     */
    public boolean sendTemplateMessage(WxTemplateMessageDto wxTemplateMessageDto) {
        String key = "wx_access_token";
        Boolean hasKey = redisTemplate.hasKey(key);
        JSONObject jsonObject = null;
        if (hasKey) { // 缓存存在
            jsonObject = redisTemplate.opsForValue().get(key);
            logger.info("from redis cache={}", jsonObject);
        } else {
            String responseData = WxUtil.getWeiXinAccessToken(accessTokenUrl);
            // String responseData = "{\"access_token\":\"9_lcuoP9KZ9OKxyMjMwui4jrA8o-K33Gke2Yt5d4o6Wv2X5rSiskUe1r8nJfjUn2-Oz1VKcVf920umJXUUI_k9_hkJP128rfAlptDo099CG_Skru_KC9hBqwldNSC4rkK9zPkYZjki0IKnxJ1NDBSeAAAUSJ\",\"expires_in\":7200}";
            if (responseData == null) { // 请求数据失败
                throw new WxException(WxEnum.ACCESS_TOKEN_FAIL);
            }
            jsonObject = JSONObject.parseObject(responseData);
            String accessToken = jsonObject.getString("access_token");
            if (accessToken == null) { // 获取失败
                Integer errCode = jsonObject.getInteger("errcode");
                String errMsg = jsonObject.getString("errmsg");
                throw new WxException(errCode, errMsg);
            }
            redisTemplate.opsForValue().set(key, jsonObject, ExpireTimeEnum.WX_ACCESS_TOKEN.getTime(), ExpireTimeEnum.WX_ACCESS_TOKEN.getTimeUnit());
            logger.info("save redis cache={}", jsonObject);
        }
        String accessToken = jsonObject.getString("access_token");
        // 发送模版消息
        String responseData = WxUtil.sendTemplateMessage(templateSendUrl + accessToken, wxTemplateMessageDto);
        if (responseData == null) { // 模版消息发送失败
            logger.info("sendTemplateMessage={}", responseData);
            return false;
        }
        logger.info("sendTemplateMessage={}", true, responseData);
        return true;
    }
}
