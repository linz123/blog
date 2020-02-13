package com.linz.util;

public enum ResultCode {

    /*成功状态码*/
    SUCCESS(1000, "成功"),
    LOGIN_SUCCESS(1000, "登录成功"),
    LOGIN_OUT_SUCCESS(1000, "退出成功"),
    ADD_IS_SUCCESS(1000, "添加成功"),
    ARTICLE_PUBLISH_SUCCESS(1000, "文章发布成功"),
    /*参数错误: 1001 - 1999*/
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    /* 用户错误 */
    USER_NOT_LOGGED(1001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "用户名或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在"),
    USER_HAS_COMMEND(2006, "用户已点过赞"),
    /*数据库错误: 3000 - 3999*/
    FAIL_TO_ADD(3001, "数据添加失败"),
    DATABASE_ERROR(3002, "数据库异常");


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}
