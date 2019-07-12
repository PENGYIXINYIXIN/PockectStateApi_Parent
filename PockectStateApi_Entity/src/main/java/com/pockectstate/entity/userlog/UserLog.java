package com.pockectstate.entity.userlog;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
@Data
public class UserLog {
    private BigInteger id ;
    private  String content;
    private Integer uid;
    private Integer type;
    private Date ctime;

}

