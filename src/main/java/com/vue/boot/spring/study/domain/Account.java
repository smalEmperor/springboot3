package com.vue.boot.spring.study.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号信息
 * @TableName account
 */
@Table(value ="account")
@Data
public class Account implements Serializable {
    /**
     * 主键
     */
    @Id(keyType  = KeyType.Auto)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态 1 正常 2 注销 3 删除
     */
    private Integer accountStatus;

    private String avatar;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(ignore = false)
    private static final long serialVersionUID = 1L;
}