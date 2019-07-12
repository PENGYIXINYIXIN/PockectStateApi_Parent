package com.pockectstate.api.pockectstateapi_servicelogin.dao;

import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
public interface UserDao {
    @Select("select id,phone,password from t_user where flag=1 and phone = #{phone}")
    @ResultType(User.class)
    User selectByPhone(String phone);

    //找回密码
    @Update("update t_user set password =#{password} where phone =#{phone} and flag =1")
    int update(@Param("phone") String phone, @Param("password") String pass);
}
