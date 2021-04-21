package com.hjq.controller;

import com.hjq.common.Enum.Code;
import com.hjq.common.utils.HttpResponseBody;
import com.hjq.mybatis.mapper.MiaoshaUserMapper;
import com.hjq.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试模块")
@RestController
public class TryController {

    @Resource
    private UserService userService;

    @ApiImplicitParam(name = "name",value = "姓名",required = true,dataTypeClass = java.lang.String.class)
    @ApiOperation(value = "测试1")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "测试2")
    @GetMapping("/select")
    public HttpResponseBody selectAllUser(@RequestParam Integer id){
        return new HttpResponseBody(Code.SUCCESS.getCode(),userService.getById(id).toString());
    }
}