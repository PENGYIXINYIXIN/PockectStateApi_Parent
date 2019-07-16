package com.pockectstate.api.pockectstateapi_serveruser.dao;


import com.pockectstate.api.common.dto.SignDto;
import com.pockectstate.entity.user.UserSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSignDao {
    int insert(UserSign record);
    //查询连续登陆天数
   UserSign selectByUidDay(@Param("uid") int uid,@Param("date") String date);
   //查询当前月的签到的数据
   List<UserSign> selectCurrMonth(@Param("uid") int uid,@Param("date") String date);
   List<UserSign> selectByUid(int uid);

   SignDto selectTj(int uid);
   //该用的当前月签到的统计记录
   long seletMonth(int uid);
   UserSign selectByUidLast(int uid);
}