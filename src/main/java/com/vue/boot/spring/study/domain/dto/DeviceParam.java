package com.vue.boot.spring.study.domain.dto;

import com.vue.boot.spring.study.common.PageDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeviceParam extends PageDto implements Serializable {

    public interface DeviceAdd {}

    public interface DeviceDelete {}

    @NotNull(message ="主键标识id", groups = {DeviceDelete.class})
    private Integer id;

    /**
     * 警员姓名
     */
    @NotBlank(message = "警员姓名", groups = {DeviceAdd.class})
    private String personNickName;

    /**
     * 警员身份证号
     */
    @NotBlank(message = "警员身份证号", groups = {DeviceAdd.class})
    private String identityCard;

    /**
     * 警员警号
     */
    @NotBlank(message = "警员警号", groups = {DeviceAdd.class})
    private String policeNumber;

    /**
     * 部门编码
     */
    @NotBlank(message = "部门编码", groups = {DeviceAdd.class})
    private String organizationCode;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 部门简称（当前名称）
     */
    @NotBlank(message = "部门简称", groups = {DeviceAdd.class})
    private String orgaSimpleName;

    /**
     * 部门全称（部门全路径）
     */
    @NotBlank(message = "部门全称", groups = {DeviceAdd.class})
    private String orgaFullName;

    /**
     * 部门名称（分局名称）
     */
    @NotBlank(message = "部门名称", groups = {DeviceAdd.class})
    private String orgaName;

    /**
     * 入网号码
     */
    @NotBlank(message = "入网号码", groups = {DeviceAdd.class})
    private String networkNumber;

    /**
     * 安全域imei1
     */
    @NotBlank(message = "安全域imei1", groups = {DeviceAdd.class})
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

}
