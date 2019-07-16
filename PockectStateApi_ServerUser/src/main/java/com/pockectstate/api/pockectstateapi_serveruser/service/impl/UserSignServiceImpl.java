package com.pockectstate.api.pockectstateapi_serveruser.service.impl;

import com.alibaba.fastjson.JSON;
import com.pockectstate.api.common.config.RedisKey_Config;
import com.pockectstate.api.common.dto.SignDto;
import com.pockectstate.api.common.model.JWTToken;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.common.util.Jwt_Util;
import com.pockectstate.api.common.util.TimeUtil;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_serveruser.dao.UserSignDao;
import com.pockectstate.api.pockectstateapi_serveruser.dao.UserWalletDao;
import com.pockectstate.api.pockectstateapi_serveruser.dao.WalletLogDao;
import com.pockectstate.api.pockectstateapi_serveruser.service.UserSignService;
import com.pockectstate.entity.user.UserSign;
import com.pockectstate.entity.user.WalletLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

/**
 * @author:Yixi
 * @date:2019/7/15
 */
@Service
public class UserSignServiceImpl implements UserSignService {

    @Autowired()
    private UserSignDao userSignDao;
    @Autowired
    private UserWalletDao userWalletDao;
    @Autowired
    private WalletLogDao walletLogDao;
   /* @Autowired
    private JedisUtil jedisUtil;*/
    private JedisUtil jedisUtil=JedisUtil.getInstance();
    //实现保存签到
    @Override
    @Transactional
    public R save(String token) {
        //实现签到
        //获取最近一次的数据
        System.out.println(token);
        JWTToken jwtToken=Jwt_Util.parseJson(jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN+token));
        UserSign userSign = userSignDao.selectByUidLast(jwtToken.getId());
        int m = new Random().nextInt(5) + 1;//几分奖励 1-5 随机
        UserSign daysign = new UserSign();
        daysign.setUid(jwtToken.getId());

        int e = 0;
        if (userSign == null) {
            //第一次签到
            e = 50;
            daysign.setDays(1);
            daysign.setSinfo("第一次签到，获取" + m + "几分");

        } else {
            //判断是否连续签到 如果是第一次签到额外奖励50
            int cd = TimeUtil.getDateDay(new Date()) - TimeUtil.getDateDay(userSign.getSdate());
            if (cd == 1) {
                //连续签到
                int d = userSign.getDays() + 1;
                if (d % 365 == 0) {
                    e = 100;
                } else if (d % 30 == 0) {
                    e = 15 + m;
                } else if (d % 5 == 0) {
                    e = 10;
                }
                if (e > 0) {
                    daysign.setSinfo("恭喜你连续签到" + d + "天，额外奖励;" + e);
                } else {
                    daysign.setSinfo("恭喜你连续签到，请继续坚持");
                }
            }else if(cd >1){
                daysign.setDays(1);
                daysign.setSinfo("你上一次连续签到"+userSign.getDays()+"天，请坚持下来");
            }
        }
        daysign.setSaward(m);
        daysign.setSextraaward(e);
        userSignDao.insert(daysign);
        //改变钱包的豆豆
        userWalletDao.updatePsbean(jwtToken.getId(),e+m);
        // 新增流水
        WalletLog walletLog = new WalletLog();
        walletLog.setInfo(daysign.getSinfo());
        walletLog.setType((byte)1);
        walletLog.setUid(jwtToken.getId());
        walletLog.setWid(daysign.getUid().intValue());
        walletLogDao.insert(walletLog);

        return R.setOK("ok",null);
        }

    //查询用户签到当前月的签到数据
    @Override
    public R queryByUid(String token) {
        String s = jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN + token);
        JWTToken jwtToken = JSON.parseObject(s, JWTToken.class);
        return R.setOK("ok",userSignDao.selectCurrMonth(jwtToken.getId(), TimeUtil.getMonth()));
    }

    @Override
    public R queryTj(String token) {
        JWTToken jwtToken = Jwt_Util.parseJson(jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN + token));

        SignDto signDto = userSignDao.selectTj(jwtToken.getId());
        signDto.setMonthDays((int)userSignDao.seletMonth(jwtToken.getId()));
    return R.setOK("ok",signDto);
    }

    //查询用户的所有签到的数据
    @Override
    public R queryUidAll(String token) {
        String s = jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN + token);
        JWTToken jwtToken = Jwt_Util.parseJson(jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN + token));
        return R.setOK("ok",userSignDao.selectByUid(jwtToken.getId()));
    }


    //检查今日是否能签到 可以签到返回true ，不可以签到返回false
    @Override
    public R checkSign(String token) {
        String s = jedisUtil.get(RedisKey_Config.JWTTOKEN_TOKEN + token);
        JWTToken jwtToken = JSON.parseObject(s, JWTToken.class);
        UserSign sign = userSignDao.selectByUidDay(jwtToken.getId(),TimeUtil.getDate());
        if(sign!=null){
            //签过到
            return  R.setERROR("今日已经签过到",null);
        }else{
            return R.setOK("今日未签到",null);
        }

    }
}
