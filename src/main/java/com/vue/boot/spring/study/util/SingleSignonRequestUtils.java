package com.vue.boot.spring.study.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * 单点登录工具类
 */
public class SingleSignonRequestUtils {


    public static void main(String[] args) throws Exception {

        String sysId = "cQNLYYP4";
        String sysKey = "14C6BB8867719C3S";//16位
        String timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());//"2023-08-20 12:15:00"
        String nonce = "238472347233467";
        String account = "zuhu001";

        //获取单点登录重定向令牌认证接口的tokenAuthReq参数值
        String tokenAuthReqValue= getTokenAuthReqValue(sysId, sysKey, timeStamp, nonce,account);
        System.out.println("tokenAuthReqValue----"+tokenAuthReqValue);

        //拼接单点登录重定向令牌认证接口url
        String singleSignonReqUrl = "https://xxxx/singleSignon?tokenAuthReq=";
        singleSignonReqUrl=singleSignonReqUrl.concat(tokenAuthReqValue);
        System.out.println("singleSignonReqUrl----"+singleSignonReqUrl);

        //checkSingleSignon(tokenAuthReqValue,sysKey);
    }

    /**
     * 按接口规范生成单点登录重定向令牌认证接口的tokenAuthReq参数值
     */
    public static String getTokenAuthReqValue(String sysId, String sysKey,String timeStamp,String nonce, String account) throws Exception {
        String digest = Base64.getEncoder().encodeToString(HashUtil.sha256((sysId + timeStamp + nonce + account).getBytes("UTF-8")));

        String tempString = timeStamp.concat("$").concat(nonce).concat("$").concat(account).concat("$").concat(digest);

        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, sysKey.getBytes(StandardCharsets.UTF_8), sysKey.getBytes(StandardCharsets.UTF_8));
        String decrypt = aes.encryptHex(tempString);
        String tokenAuthReqValue = URLEncoder.encode(sysId.concat("$").concat(decrypt), StandardCharsets.UTF_8.name());

        return tokenAuthReqValue;
    }


}
