package com.hjq.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1470160003373860808L;

    private Integer id;

    private String loginName;

    private String password;

    private String nickname;

    private String salt;

    private Date registerTime;

    private Date last_login_date;

    private Integer status;

}