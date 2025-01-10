package com.vue.boot.spring.study.domain.dto;

import com.vue.boot.spring.study.common.PageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class PushLogParams extends PageDto implements Serializable {

    public interface LogAdd {}

    public interface LogDelete {}

    public interface LogUpdate {}

    @NotNull(message = "主键标识id", groups = {LogUpdate.class, LogDelete.class})
    private Integer id;

    /**
     * imei
     */
    @NotBlank(message = "imei", groups = {LogAdd.class})
    private String imei;

    /**
     * 应用标识
     */
    @NotBlank(message = "appKey", groups = {LogAdd.class})
    private String appKey;

    /**
     * i2ii 1 i2iii 2 ii21 3 ii2iii 4 iii21 5 iii2ii
     */
    @NotNull(message = "pushType", groups = {LogAdd.class})
    private Integer pushType;

    /**
     * 成功0  失败1，默认0
     */
    @NotNull(message = "pushStatus", groups = {LogAdd.class})
    private Integer pushStatus;

    /**
     * 推送数量
     */
    private Integer pushCount;

    /**
     * 成功数量
     */
    private Integer successCount;

    /**
     * 失败数量
     */
    private Integer failCount;

    /**
     * 已读数量
     */
    private Integer readCount;

    /**
     * 未读数量
     */
    private Integer unreadCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(ignore = true)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(ignore = true)
    private LocalDate endDate;
}
