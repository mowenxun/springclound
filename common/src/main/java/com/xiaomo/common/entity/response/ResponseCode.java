package com.xiaomo.common.entity.response;

public enum ResponseCode {

    SUCCESS("000000", "成功"),
    ERROR("999999", "系统错误"),
    NEED_LOGIN("1000", "未登录"),
    ILLEGAL_ARGUMENT("1001", "参数不合法");

    private final String code;
    private final String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
