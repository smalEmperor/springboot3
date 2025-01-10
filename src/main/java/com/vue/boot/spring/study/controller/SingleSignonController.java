package com.vue.boot.spring.study.controller;

import com.vue.boot.spring.study.util.SingleSignonRequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/singlesignon")
public class SingleSignonController  {

    /**
     * 获取单点登录重定向URL
     **/
    @GetMapping(value = "/getSingleSignonReqUrl")
    public  Map<String,String>  getSingleSignonReqUrl(HttpServletRequest request) {

        String sysId = "cQNLYYP4";
         String sysKey = "14C6BB8867719C3S";//16位
        String timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());//"2023-08-20 12:15:00"
        String nonce = "238472347233467";
        String account = "zuhu001";

        //获取单点登录重定向令牌认证接口的tokenAuthReq参数值
        String tokenAuthReqValue= "";
        try {
            tokenAuthReqValue = SingleSignonRequestUtils.getTokenAuthReqValue(sysId, sysKey, timeStamp, nonce,account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tokenAuthReqValue----"+tokenAuthReqValue);

        //拼接单点登录重定向令牌认证接口url
        String singleSignonReqUrl = "https://xxxx/singleSignon?tokenAuthReq=";
        singleSignonReqUrl=singleSignonReqUrl.concat(tokenAuthReqValue);
        System.out.println("singleSignonReqUrl----"+singleSignonReqUrl);
		Map map =new HashMap();
        map.put("singleSignonReqUrl",singleSignonReqUrl);
        return map;

    }


}
