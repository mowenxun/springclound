package com.xiaomo.client.extension;

import com.xiaomo.common.entity.exception.ApplicationException;
import com.xiaomo.common.entity.response.ResponseCode;
import com.xiaomo.common.entity.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Iterator;
import java.util.Objects;

/**
 * 提供者API 全局异常统一处理
 *
 * @author donh on 2018/11/6.
 * @version 1.0
 */
@RestControllerAdvice("com.xiaomo.client.controller")
public class FeginControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse<?> errorHandler(Exception ex) {
        return new ResultResponse<Object>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ApplicationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<?> myErrorHandler(ApplicationException ex) {
        return new ResultResponse<>(ex.getCode(), ex.getMessage());
    }

    /**
     * 拦截捕获 @RequestBody 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<?> validExceptionHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResultResponse<Object>(null, "400", message);
    }

    /**
     * 拦截捕获数据绑定时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<Object> validExceptionHandler(BindException ex) {
        String message =
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResultResponse<>(null, "400", message);
    }

    /**
     * 拦截捕获 @RequestParam 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<?> validExceptionHandler(ConstraintViolationException ex) {
        Iterator<ConstraintViolation<?>> it = ex.getConstraintViolations().iterator();
        String message = "";
        if (it.hasNext()) {
            message = it.next().getMessageTemplate();
        }
        return new ResultResponse<Object>("400", message);
    }

    /**
     * 拦截捕获 @RequestBody required=true 绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<?> validExceptionHandler(HttpMessageNotReadableException ex) {
        return new ResultResponse<Object>("400", "没有请求体");
    }

    /**
     * 拦截捕获绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UnexpectedTypeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultResponse<?> validExceptionHandler(UnexpectedTypeException ex) {
        return new ResultResponse<Object>("400", "参数类型不对");
    }
}
