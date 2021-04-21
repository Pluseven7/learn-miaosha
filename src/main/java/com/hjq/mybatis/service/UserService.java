package com.hjq.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjq.mybatis.entity.User;

public interface UserService extends IService<User> {

    User findByLoginName(String loginName);
}
