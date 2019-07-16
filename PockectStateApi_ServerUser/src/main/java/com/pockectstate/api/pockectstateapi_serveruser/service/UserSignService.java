package com.pockectstate.api.pockectstateapi_serveruser.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.user.UserSign;

/**
 * @author:Yixi
 * @date:2019/7/15
 */
public interface UserSignService {
    //实现签到
    R save(String token);
    //查寻用户的当钱月份的签到数据
    R queryByUid(String token);
    //查询用户的所有的签到统计
    R queryTj(String token);
     //查询用户的所有的签到的数据
    R queryUidAll(String token);
    //检查指定的用户能否签到
    R checkSign(String token);

}
