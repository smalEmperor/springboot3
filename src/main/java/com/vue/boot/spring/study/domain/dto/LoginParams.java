package com.vue.boot.spring.study.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginParams implements Serializable {

    private String userName;

    private String password;
}
