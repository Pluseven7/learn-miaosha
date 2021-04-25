package com.hjq.mybatis.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjq.common.SysException;
import com.hjq.common.utils.HttpResponseBody;
import com.hjq.mybatis.entity.User;
import com.hjq.mybatis.mapper.UserMapper;
import com.hjq.mybatis.service.UserService;
import com.hjq.mybatis.vo.UserRegisterVo;
import org.springframework.stereotype.Service;
import sun.util.logging.resources.logging;

import javax.annotation.Resource;
import java.util.ResourceBundle;

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

    @Override
    public boolean userRegister(UserRegisterVo userRegisterVo) {
        try{
            userMapper.userRegister(userRegisterVo);
        }catch (SysException e){
            HttpResponseBody.failResponse(e);
            return false;
        }
        return true;
    }

}
