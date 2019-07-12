package com.pockectstate.api.pockectstateapi_serveruser.dao;

import com.pockectstate.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
public interface UserDao {
    @Insert("insert into t_user(phone,password,flag) values(#{phone},#{password},#{flag})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);

    @Select("select id,phone,password,flag from t_user where phone=#{phone}")
    @ResultType(User.class)
    User selectByPhone(String phone);
}
