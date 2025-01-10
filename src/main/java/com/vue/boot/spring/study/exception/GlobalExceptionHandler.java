package com.vue.boot.spring.study.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.vue.boot.spring.study.common.BaseResp;
import com.vue.boot.spring.study.common.ResultStatus;
import com.vue.boot.spring.study.util.ResultUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Set;


/**
 * @author zhoupengbing
 *  全局异常处理类
 * @date  2019年12月09日 11:02:00
 * @version v1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 这里是处理 @PathVariable和@RequestParam 验证不通过抛出的异常
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResp<?> handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder errorInfo = new StringBuilder();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append(item.getMessage()).append("不能为空");
        }
        log.warn("框架捕获到异常: {}, {}", ResultStatus.error_invalid_argument.getErrorCode(), errorInfo);
        return ResultUtil.common(ResultStatus.error_invalid_argument.getErrorCode(),errorInfo.toString());
    }

    /**
     * 这里处理@RequestBody,验证不通过抛出的异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp<?> handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("不能为空");
        }
        log.warn("框架捕获到异常: {}, {}", ResultStatus.error_invalid_argument.getErrorCode(), errorMessage);
        return ResultUtil.common(ResultStatus.error_invalid_argument.getErrorCode(), errorMessage.toString());
    }

    /**
     * 处理自定义异常
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public BaseResp<?> business(CustomException ex) {
        log.warn("框架捕获到异常: {}, {}", ex.getCode(), ex.getMsg());
        return ResultUtil.common(ex.getCode(), ex.getMsg());
    }

    /**
     * 处理404
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResp<?> handlerNoFoundException(Exception e) {
        log.warn("框架捕获到异常: {}, {}", ResultStatus.http_status_not_found.getErrorCode(), "路径不存在,请检查路径是否正确");
        return ResultUtil.common(ResultStatus.http_status_not_found.getErrorCode(), "路径不存在,请检查路径是否正确");
    }
    /**
     * 其他500异常
     * @return
     */
    @ExceptionHandler(NotLoginException.class)
    public BaseResp<?> handleNotLoginException(NotLoginException nle) {
        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        log.error("框架捕获到异常: {}, {}", ResultStatus.http_status_unauthorized.getErrorCode(), message);
        return ResultUtil.common(ResultStatus.http_status_unauthorized.getErrorCode(), message);
    }

    /**
     * 其他500异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResp<?> handleException(Exception e) {
        e.printStackTrace();
        log.error("框架捕获到异常: {}, {}", ResultStatus.http_status_internal_server_error.getErrorCode(), e.getMessage());
        return ResultUtil.error(e.getMessage());
    }
}
