package com.vue.boot.spring.study.listener;
 
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
 
import java.io.IOException;
 
/**
 * @author : Mr.Yan
 * @program : com
 * @create : 2019-03-27 18:41
 * @description : 对 @RequestMapping 返回值进行加密
 */
@Slf4j
@ControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {
 
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //这里设置成false 它就不会再走这个类了
        return true;
    }
 
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            log.info("开始对返回值进行加密操作!");
            // 定义是否解密
            System.out.println(body);
            log.info("对方法method :【" + methodParameter.getMethod().getName() + "】数据进行加密!");
            return encryptedBody(body, true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("对方法method :【" + methodParameter.getMethod().getName() + "】数据进行加密出现异常：" + e.getMessage());
            throw new RuntimeException("对方法method :【" + methodParameter.getMethod().getName() + "】数据进行加密出现异常：" + e.getMessage());
        }
    }
 
    public Object encryptedBody(Object body, Boolean encode) throws IOException {
       return body;
    }
}