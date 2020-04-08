package com.xiaomo.common.entity.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultResponse
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/25
 * @Version V1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = -1549643581827130116L;
    private String statusCode;
    private String msg;
    private T data;

    public ResultResponse() {

    }

    public ResultResponse(String statusCode) {
        this.statusCode = statusCode;
    }

//    public ResultResponse(String statusCode, T data) {
//        this.statusCode = statusCode;
//        this.data = data;
//    }

    public ResultResponse(String statusCode, String msg, T data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public ResultResponse(String statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    @JsonIgnore
    // 使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.statusCode == ResponseCode.SUCCESS.getCode();
    }

    public String getStatus() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ResultResponse<T> createBySuccess() {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResultResponse<T> createBySuccessMessage(String msg) {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

//    public static <T> ResultResponse<T> createBySuccess(T data) {
//        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), data);
//    }

    public static <T> ResultResponse<T> createBySuccess(String msg, T data) {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultResponse<T> createByError() {
        return new ResultResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ResultResponse<T> createByErrorMessage(String errorMessage) {
        return new ResultResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResultResponse<T> createByErrorCodeMessage(String errorCode, String errorMessage) {
        return new ResultResponse<T>(errorCode, errorMessage);
    }

}
