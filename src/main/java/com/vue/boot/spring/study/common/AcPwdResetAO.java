package com.vue.boot.spring.study.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @author dcs
 * @description 重置密码
 * @createTime 2021.8.23
 */
@Data
public class AcPwdResetAO {
    @NotNull(message = "账号ID不能为空")
    @JsonProperty("access_id")
    private Integer accountId;

    // @Password(min = 6, max = 30, message = "请输入6-30位数字、字母、特殊字符相结合的新密码")
    @Password
    @JsonProperty("reset_pwd")
    private String newPwd;
}
