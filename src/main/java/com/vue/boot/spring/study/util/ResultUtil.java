package com.vue.boot.spring.study.util;

import com.vue.boot.spring.study.common.BaseResp;
import lombok.experimental.UtilityClass;

/**
 * 统一返回工具类
 * @author dufa
 * @date 2020-11-27
 */
@UtilityClass
public class ResultUtil {

    public <T> BaseResp<T> ok(String msg, T data){
        return BaseResp.create(ResultStatus.SUCCESS.getErrorCode(),msg,data);
    }

    public <T> BaseResp<T> ok(T data){
       return ok(ResultStatus.SUCCESS.getErrorMsg(),data);
    }

    public <T> BaseResp<T> ok(){
        return ok(null);
    }

    public <T> BaseResp<T> error(){
        return BaseResp.create(ResultStatus.http_status_internal_server_error.getErrorCode(), ResultStatus.http_status_internal_server_error.getErrorMsg(),null);
    }

    public <T> BaseResp<T> error(String msg, T data){
        return BaseResp.create(ResultStatus.http_status_internal_server_error.getErrorCode(),msg,data);
    }

    public <T> BaseResp<T> error(T data){
        return BaseResp.create(ResultStatus.http_status_internal_server_error.getErrorCode(),ResultStatus.http_status_internal_server_error.getErrorMsg(),data);
    }

    public <T> BaseResp<T> common(Integer code, String msg) {
        return BaseResp.create(code,msg,null);
    }

    public <T> BaseResp<T> common(Integer code, String msg  , T data) {
        return BaseResp.create(code,msg,data);
    }
}
