package com.vue.boot.spring.study.controller;

import com.thunisoft.summer.component.crypto.defaultDecrypt.DESedeDecryptor;
import com.vue.boot.spring.study.common.BaseResp;
import com.vue.boot.spring.study.domain.Account;
import com.vue.boot.spring.study.domain.dto.LoginParams;
import com.vue.boot.spring.study.service.AccountService;
import com.vue.boot.spring.study.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class LoginController {

    private final AccountService accountService;
    /**
     * 登录
     */
    @PostMapping("login")
    public BaseResp<?> login(@RequestBody LoginParams loginParams) {
        Map<String, Object> info = accountService.login(loginParams);
        return ResultUtil.ok(info);
    }

    /**
     * 登录
     */
    @PostMapping("update")
    public BaseResp<?> add(@RequestBody Account account) {
        accountService.update(account);
        return ResultUtil.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("info")
    public BaseResp<?> userInfo() {
        Account info = accountService.userInfo();
        return ResultUtil.ok(info);
    }

    /**
     * 用户信息
     */
    @DeleteMapping("logout")
    public BaseResp<?> logout() {
        accountService.logout();
        return ResultUtil.ok();
    }

}
