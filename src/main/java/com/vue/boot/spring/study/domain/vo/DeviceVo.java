package com.vue.boot.spring.study.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeviceVo implements Serializable {

    private Integer id;

    /**
     * 警员姓名
     */
    private String personNickName;

    /**
     * 警员身份证号
     */
    private String identityCard;

    /**
     * 警员警号
     */
    private String policeNumber;

    /**
     * 部门编码
     */
    private String organizationCode;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 部门名称（分局名称）
     */
    private String orgaName;

    /**
     * 入网号码
     */
    private String networkNumber;

    /**
     * 安全域imei1
     */
    private String safeImei1;

}
