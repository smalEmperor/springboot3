package com.vue.boot.spring.study.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.thunisoft.summer.component.crypto.defaultDecrypt.DESedeDecryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class NacosController {

    @NacosValue(value = "${test:5522}", autoRefreshed = true)
    private String test;

    /**
     * 在yaml配置格式得时候读不到
     */
//    @NacosValue(value = "${server.port}", autoRefreshed = true)
//    private String port;

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/get")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @GetMapping("/value")
    public String getValue() {
        DESedeDecryptor dec = new DESedeDecryptor();
        System.out.println(dec.decrypt("dOmjrF+AWL/oGTJ9FgCeuA=="));
        return test;
    }

    public static void main(String[] args) {
        DESedeDecryptor dec = new DESedeDecryptor();
        System.out.println(dec.decrypt("SHYQbtX5ftY="));
        System.out.println(dec.decrypt("r2Xdjyf0fPDDn55jn7fiHg=="));
    }

}
