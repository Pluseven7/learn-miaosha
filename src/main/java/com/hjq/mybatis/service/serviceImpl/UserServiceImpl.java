package com.hjq.mybatis.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjq.mybatis.entity.User;
import com.hjq.mybatis.mapper.UserMapper;
import com.hjq.mybatis.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
