package com.hjq.mybatis;

import com.hjq.mybatis.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户注册Vo")
public class UserRegisterVo extends User {

    @ApiModelProperty(name = "登录名")
    private String loginName;

    @ApiModelProperty(name = "登录密码")
    private String password;

    @ApiModelProperty(name = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "邮箱地址")
    private String email;


}
