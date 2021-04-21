package com.hjq.mybatis.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjq.common.SysException;
import com.hjq.mybatis.entity.User;
import com.hjq.mybatis.mapper.UserMapper;
import com.hjq.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User findByLoginName(String loginName) {
        try {
            User user = userMapper.selectByLoginName(loginName);
            return user;
        }catch (SysException e){

        }
        return null;
    }
}
