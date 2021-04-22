package com.hjq.controller;

import com.hjq.common.SysException;
import com.hjq.common.utils.HttpResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户账号")
@RestController
public class UserController {



    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginname",value = "登录名",required = true,dataTypeClass = java.lang.String.class),
        @ApiImplicitParam(name = "password",value = "密码",required = true,dataTypeClass = java.lang.String.class)
    })
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public Map login(String loginname, String password){
        Map<String,Object> map = new HashMap<>();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(loginname, password);
        try{
            subject.login(token);
            //获取sessionid,传到前台
            Serializable sessionId = subject.getSession().getId();
            map.put("message", HttpResponseBody.successResponse().getMsg());
            map.put("session_id", sessionId);
            map.put("code", HttpResponseBody.successResponse().getCode());
            return map;
        }catch (SysException e){
            map.clear();
            e.printStackTrace();
            map.put("message", HttpResponseBody.failResponse(e).getMsg());
            map.put("code", HttpResponseBody.failResponse(e).getCode());
            return map;
        }
    }

}
