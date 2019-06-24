package com.lf.demo.context;

import com.alibaba.fastjson.annotation.JSONField;
import com.lf.demo.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * PACKAGE      :  com.jiahui.eappt.context
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :  管理员系统上下文
 */
public class AdminSystemContext implements Serializable {

    // 请求
    @JSONField(serialize = false)
    private HttpServletRequest request;
    // 响应
    @JSONField(serialize = false)
    private HttpServletResponse response;
    // 请求地址
    private String requestUri;
    // IP
    private String ip;
    private String ua;
    // 是否进行登录
    private boolean doLongin = Boolean.FALSE;
    // 系统接收时间， 单位 毫秒
    private Long systemReceiveTime;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 语言
     */
    private String language;

    public AdminSystemContext() {

    }

    public AdminSystemContext(HttpServletRequest request, HttpServletResponse response,
                              String requestUri) {
        this.request = request;
        this.response = response;
        this.requestUri = requestUri;
        if (null != request) {
            this.ip = HttpUtil.getIP(request);
            this.ua = HttpUtil.getUA(request);
        }
        this.systemReceiveTime = System.currentTimeMillis();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public boolean isDoLongin() {
        return doLongin;
    }

    public void setDoLongin(boolean doLongin) {
        this.doLongin = doLongin;
    }

    public Long getSystemReceiveTime() {
        return systemReceiveTime;
    }

    public void setSystemReceiveTime(Long systemReceiveTime) {
        this.systemReceiveTime = systemReceiveTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}