package com.vue.boot.spring.study.listener;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
 
import java.io.*;
import java.lang.reflect.Type;
 
/**
 * @author : Mr.Yan
 * @program : com
 * @create : 2019-03-27 18:12
 * @description : 对 @RequstMapping 中 初始化解密动作
 */
@Slf4j
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
 
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        //这里设置成false 它就不会再走这个类了
        return true;
    }
 
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        try {
            log.info("开始对接收值进行解密操作");
            return new MyHttpInputMessage(httpInputMessage, true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("对方法method :【" + methodParameter.getMethod().getName() + "】数据进行解密出现异常：" + e.getMessage());
            throw new RuntimeException("对方法method :【" + methodParameter.getMethod().getName() + "】数据进行解密出现异常：" + e.getMessage());
        }
    }
 
    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
 
    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
 
 
    //这里实现了HttpInputMessage 封装一个自己的HttpInputMessage
    class MyHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;
 
        public MyHttpInputMessage(HttpInputMessage httpInputMessage, boolean encode) throws IOException {
            this.headers = httpInputMessage.getHeaders();
            // 解密操作
            this.body = decryptBody(httpInputMessage.getBody(), encode);
        }
 
        @Override
        public InputStream getBody() {
            return body;
        }
 
        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
 
    /**
     * 对流进行解密操作
     *
     * @param in
     * @return
     */
    public InputStream decryptBody(InputStream in, Boolean encode) throws IOException {
        // 获取 json 字符串
//        String bodyStr = IOUtils.toString(in, "UTF-8");
//        if (StrUtil.isEmpty(bodyStr)) {
//            throw new RuntimeException("无任何参数异常!");
//        }
        return in;
    }
}