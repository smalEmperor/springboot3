package com.vue.boot.spring.study.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageDto implements Serializable {

    private static final long serialVersionUID = -4152721444152979429L;

    private Integer size = 10;

    private Integer current = 1;
}
