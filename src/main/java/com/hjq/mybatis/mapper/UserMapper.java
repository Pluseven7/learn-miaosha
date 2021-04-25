package com.hjq.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjq.mybatis.entity.User;
import com.hjq.mybatis.vo.UserRegisterVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM USER WHERE login_name = #{loginName}")
    User selectByLoginName(@Param("loginName")String loginName);

    @Insert("<script> INSERT INTO USER "
            + "(loginName,password,nickname,email,salt,registerTime,status)"
            + " VALUES "
            + "<foreach collection='userRegisterVo' item='user' index='index' separator=',' >"
            + "</foreach>"+"/<script>"
    )
    boolean userRegister(UserRegisterVo userRegisterVo);
}