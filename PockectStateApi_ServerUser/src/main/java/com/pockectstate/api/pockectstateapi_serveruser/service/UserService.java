package com.pockectstate.api.pockectstateapi_serveruser.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.common.dto.UserDto;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
public interface UserService {
    R save(UserDto userDto);
    R queryByPhone(String phone );

}
