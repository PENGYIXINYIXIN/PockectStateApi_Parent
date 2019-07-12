package com.pockectstate.api.common.test;

import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.util.Jwt_Util;
import org.junit.Test;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
public class JWT_TEST {
    @Test
    public void ti(){
        String m = "15713991187";
        IdGenerator idGenerator = new IdGenerator();

        String pass = Jwt_Util.createJWT(idGenerator.nextId()+"",30,m);
        System.out.println(pass);

        System.out.println(Jwt_Util.parseJWT(pass));
    }
}
