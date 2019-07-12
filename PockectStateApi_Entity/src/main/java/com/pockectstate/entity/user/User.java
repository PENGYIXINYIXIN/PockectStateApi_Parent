package com.pockectstate.entity.user;

import lombok.Data;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
@Data
public class User {
    private Integer id ;
    private String phone;
    private String password;
    private  Integer flag;//1 正常  2 失效
}
