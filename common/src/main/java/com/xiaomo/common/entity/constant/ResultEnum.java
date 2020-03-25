package com.xiaomo.common.entity.constant;

/**
 * Created by donh on 2019/1/8.
 */
public enum ResultEnum implements BaseEnumType {
    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    SUCCESS("200", "success"),
    USER_EXIST("100002", "用户已存在！"),
    NETWORK_LIMIT("100001", "网络限流！"),
    TOKEN_NOT_FOUND("200001", "token不能为空！"),
    TENANT_NOT_FOUND("200002", "tenantId不能为空！"),
    QUERY_PARAM_NOT_FOUND("200003", "查询参数不能为空"),
    REQUEST_PARAM_NOT_FOUND("200003", "请求参数不能为空"),
    UPDATE_DEPARTMENT_PRODUCTLINE_ERROR("500", "保存销售组织关联的产品线失败"),

    PRODUCTLINE_BINDING("300001", "产品线已绑定,不能删除"),
    SALE_BINDING("300002", "销售组织已绑定,不能删除"),

    ELEMENT_RESOURCE_ALREADY_USE("400001", "元素已经被使用，请解除关系后删除"),
    ELEMENT_RESOURCE_NOT_EXISTS("400002", "元素不存在"),


    CORPORATE_PRODUCTLINE_NULL_ERROR("300003","该法人没有产品线"),
    SALE_CODE_EXIST("300004","销售组织编码已存在"),
    CORPORATE_UPDATE_ERROR("300005","法人更新失败"),
    PRODUCTLINE_UPDATE_ERROR("300006","部门产品线更新失败"),
    PERMISSION_BINDING_ERROR("300007","已被账号引用,不能删除"),
    SALE_BINDING_ERROR("300008","已被业务员引用,不能删除"),
    PRODUCTLINE_BINDING__SALE_ERROR("300009","该产品线没有服务的销售组织"),
    CORPORATE_BINDING__SALE_ERROR("300009","该法人没有服务的销售组织"),

    USER_NOT_EXISTS("400001", "该用户不存在"),
    USER_PASSWORD_ERROR("400002", "用户密码错误"),
    CORPORATE_CODE_EXIST("400004","法人组织编码已存在"),
    PRODUCTLINE_EXIST_ERROR("400005","产品线已存在,请勿重复")

    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}