package com.lf.demo.vo;

import com.lf.demo.util.StateUtil;

import java.io.Serializable;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc
 * @see
 * @since 1.0
 */
public class ResultBody<T> implements Serializable {


    /**
     * 语言
     */
    private String language;
    private Integer errorCode;
    private String msg;
    private T data;
    /**
     * 堆栈信息, 开发阶段返回
     */
    private Object stack;

    public ResultBody() {

    }

    public ResultBody(T data) {
        this.errorCode = StateUtil.SUCCESS;
        this.msg = StateUtil.message.get(this.errorCode);
        this.data = data;
    }

    public ResultBody(T data, String language) {
        this.language = language;
        this.errorCode = StateUtil.SUCCESS;
        this.msg = StateUtil.message.get(this.errorCode);
        this.data = data;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getStack() {
        return stack;
    }

    public void setStack(Object stack) {
        this.stack = stack;
    }
}
