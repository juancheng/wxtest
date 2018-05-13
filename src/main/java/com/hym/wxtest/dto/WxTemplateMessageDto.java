package com.hym.wxtest.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 微信模版消息模版
 * {
 "touser": "OPENID",
 "template_id": "TEMPLATE_ID",
 "page": "index",
 "form_id": "FORMID",
 "data": {
 "keyword1": {
 "value": "339208499",
 "color": "#173177"
 },
 "keyword2": {
 "value": "2015年01月05日 12:30",
 "color": "#173177"
 },
 "keyword3": {
 "value": "粤海喜来登酒店",
 "color": "#173177"
 } ,
 "keyword4": {
 "value": "广州市天河区天河路208号",
 "color": "#173177"
 }
 },
 "emphasis_keyword": "keyword1.DATA"
 }

 touser	是	接收者（用户）的 openid
 template_id	是	所需下发的模板消息的id
 page	否	点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
 form_id	是	表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
 data	是	模板内容，不填则下发空模板
 color	否	模板内容字体的颜色，不填默认黑色
 emphasis_keyword	否	模板需要放大的关键词，不填则默认无放大
 */
public class WxTemplateMessageDto implements Serializable{
    private String touser;
    private String template_id;
    private String page;
    private String form_id;
    private List<KeyWords> data;
    private String emphasis_keyword;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }


    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public List<KeyWords> getData() {
        return data;
    }

    public void setData(List<KeyWords> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WxTemplateMessageDto{" +
                "touser='" + touser + '\'' +
                ", template_id='" + template_id + '\'' +
                ", page='" + page + '\'' +
                ", form_id='" + form_id + '\'' +
                ", data=" + data +
                ", emphasis_keyword='" + emphasis_keyword + '\'' +
                '}';
    }

    class KeyWords {
        private String value;
        private String color;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "KeyWords{" +
                    "value='" + value + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
