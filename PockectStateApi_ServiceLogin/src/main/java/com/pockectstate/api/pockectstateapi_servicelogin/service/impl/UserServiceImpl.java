package com.pockectstate.api.pockectstateapi_servicelogin.service.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.config.Key_Config;
import com.pockectstate.api.common.config.RedisKey_Config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.model.JWTToken;
import com.pockectstate.api.common.util.EncryptionUtil;

import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.common.util.Jwt_Util;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_servicelogin.dao.UserDao;
import com.pockectstate.api.pockectstateapi_servicelogin.service.UserService;
import com.pockectstate.api.pockectstateapi_servicelogin.util.DeviceKeyUtil;
import com.pockectstate.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private  UserDao userDao;
    /*@Autowired
    private JedisUtil jedisUtil;
   */
    private JedisUtil jedisUtil = JedisUtil.getInstance();
    @Autowired
    private IdGenerator idGenerator ;
    //登陆
    @Override
    public R login(LoginDto loginDto) {
        //验证当前账号是否被冻结
        if(jedisUtil.exists(RedisKey_Config.LOGINGFORCE+loginDto.getPhone())){
            //被冻结
            return R.setERROR("账号已经被冻结，剩余时间："+getTTL(RedisKey_Config.LOGINGFORCE+loginDto.getPhone()),null);
        }else {
            boolean login = false;
            //验证手机号是否存在
            User user = userDao.selectByPhone(loginDto.getPhone());
            if(user!=null){
                //验证密码是否正确
                if(Objects.equals(user.getPassword(), EncryptionUtil.AESEnc(Key_Config.PASSKEY,loginDto.getPassword()))){
                    //登陆成功之后生成令牌 存储到redis里面
                    JWTToken jwtToken = new JWTToken();
                    jwtToken.setDevice(loginDto.getDevice());
                    jwtToken.setDeviceMac(loginDto.getDeviceMac());
                    jwtToken.setPhone(loginDto.getPhone());
                    jwtToken.setId(user.getId());
                    jwtToken.setNo(idGenerator.nextId()+"");
                    //JSON对象转换成json格式字符串
                    String jsonToken = JSON.toJSONString(jwtToken);
                    String token = Jwt_Util.createJWT(idGenerator.nextId()+"",Jwt_Config.JETTOKENTIME,jsonToken);
                    //存储到redis里面
                    //当前的令牌  值为对应的JwtToken的JSON对象
                    jedisUtil.setExpire(RedisKey_Config.JWTTOKEN_TOKEN+token,jsonToken,Jwt_Config.JETTOKENTIME*60);
                    //存储的是当前的设备 和账号信息 值为对应的令牌
                    String dk = DeviceKeyUtil.createKey(jwtToken);
                    jedisUtil.setExpire(RedisKey_Config.JWTTOKEN_DEVICE+dk,token,Jwt_Config.JETTOKENTIME*60);
                    login=true;
                    return R.setOK("ok",token);
                }
            }
            //登录失败  redis里面次数加一
            if(!login) {
                String k = RedisKey_Config.LOGINGERROR + loginDto.getPhone();
                if (jedisUtil.exists(k)) {
                    //取值
                    int c = Integer.parseInt(jedisUtil.get(k));
                    if(c >= 2){
                        jedisUtil.setExpire(RedisKey_Config.LOGINGFORCE+loginDto.getPhone(),loginDto.getPhone(),15*60);
                        return R.setERROR("您已经连续输错三次 账号被冻结15分钟",null);
                    }
                    jedisUtil.setExpire(k,c+1+"",(int)jedisUtil.ttl(k));
                } else {
                    //第一次失败
                jedisUtil.setExpire(k, 1 + "", 300);
            }
            }
            return R.setERROR("账号或者手机号码不正确",null);
        }
    }

    @Override
    public R loginout(String token) {
        String js = Jwt_Util.parseJWT(token);
        if(js != null || js.length()>0){
            //删除对应的令牌
            jedisUtil.del(RedisKey_Config.JWTTOKEN_TOKEN+token);
            JWTToken jwtToken = JSON.parseObject(js, JWTToken.class);
            //删除设备信息
            jedisUtil.del(RedisKey_Config.JWTTOKEN_DEVICE+token);
        }
        return null;
    }
    //找回密码
    @Override
    public R goback(UserDto userDto) {
        if(jedisUtil.exists(RedisKey_Config.LOGINGFORCE+userDto.getPhone())){
            int j = userDao.update(userDto.getPhone(), EncryptionUtil.AESEnc(Jwt_Config.JWTKEY, userDto.getPsw()));
            if(j>0){
                //修改密码成功
                Set<String> keys = jedisUtil.keys(RedisKey_Config.JWTTOKEN_DEVICE + userDto.getPhone() + "_*");
                //集合变成数组
                String[] arr = new String[keys.size()];
                int i = 0;
                for (String k:
                        keys) {
                    arr[i]=k;
                    i++;
                }
                jedisUtil.del(arr);
                return R.setOK("密码找回成功，请妥善保管",null);
            }
            return R.setERROR("密码找回失败，请稍后再试",null);
        }else {
            return R.setERROR("账号冻结中，剩余"+getTTL(RedisKey_Config.LOGINGFORCE+userDto.getPhone()),null);
        }

    }

    /*校验令牌的有效性
    * 校验令牌是否符合JWT规则
    *
    * */
    @Override
    public R checkToken(String token) {
        //验证token是否有效
        //基于JWT  校验令牌符合JWT 规则
        if(Jwt_Util.checkJWT(token)){
            //校验成功 符合jwt格式
            if(jedisUtil.exists(RedisKey_Config.JWTTOKEN_DEVICE+token)){
                //在校验手机号和设备对应的令牌和当前令牌是否一致
                String json = Jwt_Util.parseJWT(token);//json 格式的字符串  里面存的就是JWTToken里面的内容
                //解析json格式的字符串
                JWTToken jwtToken = JSON.parseObject(json,JWTToken.class);
                String dk = DeviceKeyUtil.createKey(jwtToken);
                if (jedisUtil.exists(RedisKey_Config.JWTTOKEN_DEVICE+dk)){
                    //取出设备对应的令牌和当前令牌进行比对
                    String t = jedisUtil.get(RedisKey_Config.JWTTOKEN_DEVICE+dk);
                    if(Objects.equals(t,token)){
                        return R.setOK("ok",null);
                    }else {
                        jedisUtil.del(RedisKey_Config.JWTTOKEN_TOKEN+token);
                        return R.setERROR("已经在其他设备上登陆,被迫下线",null);
                    }
                }
            }
        }
        return R.setERROR("登陆失效请重新登陆",null);
    }
    private String getTTL(String key){
        return jedisUtil.ttl(key)/60+"分钟";
    }
}
