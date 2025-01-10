package com.vue.boot.spring.study.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.vue.boot.spring.study.domain.Account;
import com.vue.boot.spring.study.domain.dto.LoginParams;
import com.vue.boot.spring.study.exception.CustomException;
import com.vue.boot.spring.study.mapper.AccountMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    public Map<String, Object> login(LoginParams loginParams) {
        log.info("登录参数" + JSONUtil.toJsonStr(loginParams));
        Account account = accountMapper.selectOneByQuery(QueryWrapper.create()
                .select(Account::getId, Account::getPassword)
                .where(Account::getUserName).eq(loginParams.getUserName())
                .and(Account::getAccountStatus).eq(1));
        if (ObjectUtils.isEmpty(account)) {
            throw new CustomException(400, "您登录得账号不存在");
        }
        if (!account.getPassword().equals(loginParams.getPassword())) {
            throw new CustomException(400, "您登录得密码不正确");
        }
        StpUtil.login(account.getId());
        Map<String, Object> result = new HashMap<>(4);
        result.put(StpUtil.getTokenName(), StpUtil.getTokenValue());
        log.info("登录返回" + JSONUtil.toJsonStr(result));
        return result;
    }

    public Account userInfo() {
        Object loginId = StpUtil.getLoginId();
        return accountMapper.selectOneByQuery(QueryWrapper.create()
                .where(Account::getId).eq(loginId));
    }

    public void logout() {
        StpUtil.logout();
    }

    public void add(Account account) {
        accountMapper.insert(account);
    }

    public void setAvatar(Account account) {
        accountMapper.update(account);
    }

    public void update(Account account) {
        accountMapper.update(account);
    }
}




