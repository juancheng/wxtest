package com.hym.wxtest.service;

import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.dto.WxTemplateMessageDto;
import com.hym.wxtest.exception.WxException;

/**
 * 为微信操作提供各种服务的业务逻辑层接口
 */
public interface WxService {
    /**
     * 返回微信access_token
     * @return
     */
    BaseResult getWxAccessToken() throws WxException;

    /**
     * 微信发送模版消息
     * @param wxTemplateMessageDto
     * @return
     */
    boolean sendTemplateMessage(WxTemplateMessageDto wxTemplateMessageDto) throws WxException;
}
