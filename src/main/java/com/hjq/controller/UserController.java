package com.hjq.controller;

import com.hjq.common.Enum.Code;
import com.hjq.common.SysException;
import com.hjq.common.utils.HttpResponseBody;
import com.hjq.mybatis.service.UserService;
import com.hjq.mybatis.vo.UserRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户账号")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginName",value = "登录名",required = true,dataTypeClass = java.lang.String.class),
        @ApiImplicitParam(name = "password",value = "密码",required = true,dataTypeClass = java.lang.String.class)
    })
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public Map login(String loginName, String password){
        Map<String,Object> map = new HashMap<>();
        Subject subject= SecurityUtils.getSubject();
        subject.getPrincipals();
        UsernamePasswordToken token=new UsernamePasswordToken(loginName, password);
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


//    @ApiImplicitParam(name = "register",value = "注册信息",type = "body",required = true,dataTypeClass = java.lang.String.class)
//    @ApiOperation(value = "注册")
//    @PostMapping("/register")
//    @ResponseBody
//    public String userRegister(@RequestBody UserRegisterVo userRegisterVo){
//        String loginName = userRegisterVo.getLoginName();
//        String password = userRegisterVo.getPassword();
//        if(userService.userRegister(userRegisterVo)){
//            return "redirect:login";
//        }else {
//            return Code.USER_REGISTER_ERROR.getMsg();
//        }
//    }

}
