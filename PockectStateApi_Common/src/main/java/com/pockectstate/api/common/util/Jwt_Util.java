package com.pockectstate.api.common.util;

import com.pockectstate.api.common.config.Jwt_Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

//实现对JWT的封装处理
public class Jwt_Util {
    //生成
    public static String createJWT(String id,int minutes,String content){
        //1、创建加密的技术
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //创建JWT建造者对象
        JwtBuilder jwtBuilder= Jwts.builder();
        //设置Jwt相关信息
        jwtBuilder.setId(id);//id唯一
        jwtBuilder.setIssuedAt(new Date());//开始时间
        jwtBuilder.setExpiration(TimeUtil.getMinutes(minutes));//设置时效时间
        jwtBuilder.setSubject(content);//设置jwt中的内容
        jwtBuilder.signWith(signatureAlgorithm,createKey());
        //生成JWT
        return jwtBuilder.compact();

    }
    //解析
    public static String parseJWT(String token){
        SecretKey key = createKey();
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    //校验令牌是否符合规则
    public static boolean checkJWT(String token){
        String j = parseJWT(token);
        if (j==null || j.length()==0){
            return false;
        }else {
            return true;
        }

    }

    //生成秘钥
    private static SecretKey createKey(){
        byte[] keys= Jwt_Config.JWTKEY.getBytes();
        SecretKey key = new SecretKeySpec(keys,0,keys.length,"AES");
        return key;
    }



}
