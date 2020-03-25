package com.xiaomo.common.entity.exception;

/**
 * @ClassName ApplicationException
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/3/25
 * @Version V1.0
 **/

import com.xiaomo.common.entity.constant.BaseEnumType;

import java.io.Serializable;
import java.text.MessageFormat;

public class ApplicationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1354043731046864103L;
    private String code;

    public ApplicationException() {
    }

    public ApplicationException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ApplicationException(BaseEnumType baseEnumType) {
        super(baseEnumType.getMsg());
        this.code = baseEnumType.getCode();
    }

    public ApplicationException(String msg, Object... arguments) {
        super(MessageFormat.format(msg, arguments));
        this.code = "500";
    }

    public ApplicationException(BaseEnumType baseEnumType, Object... arguments) {
        super(MessageFormat.format(baseEnumType.getMsg(), arguments));
        this.code = baseEnumType.getCode();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}