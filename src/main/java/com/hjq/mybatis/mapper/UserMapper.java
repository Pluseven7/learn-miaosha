package com.hjq.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjq.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}