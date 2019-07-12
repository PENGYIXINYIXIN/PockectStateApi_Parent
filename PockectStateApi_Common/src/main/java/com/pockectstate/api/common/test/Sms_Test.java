package com.pockectstate.api.common.test;

import com.pockectstate.api.common.util.JuHeSms_Util;
import com.pockectstate.api.common.util.Random_Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
public class Sms_Test {
    @Test
    public  void test01() throws UnsupportedEncodingException {
        int code = Random_Util.createNum(6);
        System.out.println(JuHeSms_Util.sendMsg("18838964498",code));
        System.out.println(code);
    }
}
