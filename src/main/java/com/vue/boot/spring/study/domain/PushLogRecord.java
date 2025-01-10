package com.vue.boot.spring.study.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 推送系统日志记录表
 * @TableName push_log_record
 */
@Table(value ="push_log_record")
@Data
public class PushLogRecord implements Serializable {
    /**
     * 主键
     */
    @Id(keyType  = KeyType.Auto)
    private Integer id;

    /**
     * 设备imei
     */
    private String imei;

    /**
     * 应用标识
     */
    private String appKey;

    /**
     * i2ii 1 i2iii 2 ii21 3 ii2iii 4 iii21 5 iii2ii
     */
    private Integer pushType;

    /**
     * 成功0  失败1，默认0
     */
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

    /**
     * 成功率
     */
    private Double successRate;

    /**
     * 失败率
     */
    private Double failRate;

    /**
     * 推送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    /**
     * 年份
     */
    private Integer recordYear;

    /**
     * 月份
     */
    private Integer recordMonth;

    /**
     * 日
     */
    private Integer recordDay;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(ignore = false)
    private static final long serialVersionUID = 1L;
}