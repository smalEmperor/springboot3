package com.vue.boot.spring.study.common;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

public class PasswordValidate implements ConstraintValidator<Password, String> {

    @Value("${default.passwordCheckRule}")
    private String passwordCheckRule;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StrUtil.isBlank(s)) {
            return false;
        }
        if (s.contains(" ")) {
            return false;
        }
        String regexp = passwordCheckRule;
        if (!Pattern.matches(regexp, s)) {
            return false;
        }
        return true;
    }
}
