package com.pockectstate.message.pockectstatemsg_servermsg.dao;

import com.pockectstate.entity.msg.SmsSend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsSendMapper {
    int insert(SmsSend record);
    List<SmsSend> selectAll();
    SmsSend selectByMsg(@Param("phone") String phone, @Param("msg") String msg);
}