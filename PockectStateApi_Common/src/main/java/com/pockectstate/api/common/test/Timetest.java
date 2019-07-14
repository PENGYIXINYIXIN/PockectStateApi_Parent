package com.pockectstate.api.common.test;

import com.pockectstate.api.common.util.TimeUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:Yixi
 * @date:2019/7/13
 */
public class Timetest {
    @Test
    public  void t1(){
        String date = TimeUtil.getTime();
        //System.out.println(date);
        Date days = TimeUtil.getDays(3);
        //String format = new SimpleDateFormat("yyyy-MM-dd").format(days);
        //System.out.println(format);
        Date minutes = TimeUtil.getMinutes(30);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(minutes);
        System.out.println(format);
        String format1 = TimeUtil.getFormat(new Date());
        System.out.println(format1);

    }
}
