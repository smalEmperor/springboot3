package com.vue.boot.spring.study.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.*;
import com.mybatisflex.core.mask.Masks;
import com.vue.boot.spring.study.domain.dto.DeviceParam;
import com.vue.boot.spring.study.domain.vo.DeviceVo;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 人员信息表
 * @TableName member_device
 */
@Table(value ="member_device")
@Data
@AutoMappers({@AutoMapper(target = DeviceParam.class),@AutoMapper(target = DeviceVo.class)})
public class MemberDevice implements Serializable {
    /**
     * 主键id
     */
    @Id(keyType  = KeyType.Auto)
    private Integer id;

    /**
     * 警员姓名
     */
    private String personNickName;

    /**
     * 警员身份证号
     */
    @ColumnMask(Masks.ID_CARD_NUMBER)
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
     * 人员状态 0正常  1冻结 2 调出 3退休 4注销
     */
    private Integer personStatus;

    /**
     * 部门简称（当前名称）
     */
    private String orgaSimpleName;

    /**
     * 部门全称（部门全路径）
     */
    private String orgaFullName;

    /**
     * 部门名称（分局名称）
     */
    private String orgaName;

    /**
     * 入网号码
     */
    @ColumnMask(Masks.MOBILE)
    private String networkNumber;

    /**
     * 安全域imei1
     */
    private String safeImei1;

    /**
     * 安全域imei2
     */
    private String safeImei2;

    /**
     * 个人域imei1
     */
    private String personImei1;

    /**
     * 个人域imei2
     */
    private String personImei2;

    /**
     * 设备类型
     */
    private String deviceType;

    @Column(ignore = true)
    private String onlineStatus = "离线";

    /**
     * 数据更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 数据创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 个人域ip
     */
    private String ip1;

    /**
     * 安全域ip
     */
    private String ip2;

    @Column(ignore = false)
    private static final long serialVersionUID = 1L;
}