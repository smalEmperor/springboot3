package com.vue.boot.spring.study.exception;


import com.vue.boot.spring.study.common.ResultStatus;

/**
 * @author zhoupengbing
 *  自定义异常类
 * @date  2019年12月09日 11:02:00
 * @version v1.0.0
 */
public class CustomException extends RuntimeException{

    private Integer code;
    private String msg;

    /**
     * 继承exception，加入错误状态值
     * @param resultStatus
     */
    public CustomException(ResultStatus resultStatus) {
        this.msg = resultStatus.getErrorMsg();
        this.code = resultStatus.getErrorCode();
    }

    /**
     * 自定义错误信息
     * @param message
     * @param code
     */
    public CustomException(Integer code, String message) {
        this.msg = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
