package com.hym.wxtest.web;

import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.dto.WxResult;
import com.hym.wxtest.exception.WxException;
import com.hym.wxtest.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供微信服务接口api
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private WxService wxService;

    @GetMapping("/accessToken")
    public BaseResult getWxAccessToken() {
        try {
            return wxService.getWxAccessToken();
        } catch (WxException e) {
            return new WxResult(e);
        }
    }
}
