package com.hym.wxtest.entity;

import java.util.List;

/**
 * 邮件信息实体类
 */
public class MailEntity {
    // 邮件标题
    private String title;
    // 邮件内容
    private String content;
    // 内容格式
    private String contentType;
    // 接收邮件地址的集合
    private List<String> toAddress;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<String> getToAddress() {
        return toAddress;
    }

    public void setToAddress(List<String> toAddress) {
        this.toAddress = toAddress;
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", toAddress=" + toAddress +
                '}';
    }
}
