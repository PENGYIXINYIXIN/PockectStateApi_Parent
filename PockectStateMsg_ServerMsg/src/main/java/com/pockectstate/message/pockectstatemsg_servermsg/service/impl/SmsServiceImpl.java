package com.pockectstate.message.pockectstatemsg_servermsg.service.impl;

import com.netflix.discovery.converters.Auto;
import com.pockectstate.api.common.config.RedisKey_Config;
import com.pockectstate.api.common.config.Redis_Config;
import com.pockectstate.api.common.psenum.MsgLogType;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.common.util.JuHeSms_Util;
import com.pockectstate.api.common.util.Random_Util;
import com.pockectstate.api.common.util.TimeUtil;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.msg.MsgLog;
import com.pockectstate.entity.msg.SmsRestset;
import com.pockectstate.entity.msg.SmsSend;
import com.pockectstate.message.pockectstatemsg_servermsg.dao.MsgLogMapper;
import com.pockectstate.message.pockectstatemsg_servermsg.dao.SmsRestsetMapper;
import com.pockectstate.message.pockectstatemsg_servermsg.dao.SmsSendMapper;
import com.pockectstate.message.pockectstatemsg_servermsg.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;


/**
 * @author:Yixi
 * @date:2019/7/10
 */
@Service
public class SmsServiceImpl implements SmsService {
     @Autowired
     private SmsSendMapper smsSendMapper;
     @Autowired
     private MsgLogMapper msgLogMapper;

     @Autowired
     private JedisUtil jedisUtil;
    // private JedisUtil jedisUtil=JedisUtil.getInstance();;
     @Autowired
     private SmsRestsetMapper smsRestsetMapper;
     //保存验证码  保存操作日志

    @Override
    public R save(SmsSend smsSend) {
        smsSendMapper.insert(smsSend);
        MsgLog log = new MsgLog();
        log.setType(MsgLogType.validatecode.getIndex());
        log.setContent("发送："+smsSend.getPhone());
        msgLogMapper.insert(log);
        return R.setOK("ok",null);
    }

    @Override//校验验证码
    public R checkCode(String phone, int code ) {
        //验证验证码是否失效
        //校验是否存在 如果不存在就不用了校验
        if(jedisUtil.exists(RedisKey_Config.VCODE_CODE+phone)){
            //存在 进行校验
            String s = jedisUtil.get(RedisKey_Config.VCODE_CODE + phone);
            if (code == Integer.parseInt(s)){
                return R.setOK("ok",null);
            }else {
                return R.setERROR("验证码错误，请重新输入",null);
            }
        }else {
            return  R.setERROR("验证码已失效，请重新获取",null);
        }

    }


    //发送短信  发送的短信内容要存到数据库的
    @Override
    @Transactional
    public R sendSms(String phone,String ip) {
        boolean issend = true;//是否可以发送短信
        String msg = "";//记录返回的内容
        //1、验证手机号是否可发
        if(jedisUtil.exists(RedisKey_Config.VCODE_FIRST+phone)){
            //一分钟内已经发过
            msg = "一分钟内已经发送过请查看您的手机";
            issend = false;
        }else if(jedisUtil.exists(RedisKey_Config.VCODE_SECOND+phone)){
            //十分钟3次
            //获取十分钟内有发送验证码的次数

            int c = Integer.parseInt(jedisUtil.get(RedisKey_Config.VCODE_SECOND+phone));
            if(c >=3){
                msg = "十分钟内次数已经达到上限";
                issend = false;

            }
        }else if (jedisUtil.exists(RedisKey_Config.VCODE_THREE+phone)){
            //一小时4次
            int c = Integer.parseInt(jedisUtil.get(RedisKey_Config.VCODE_THREE+phone));
            if(c >=4){
                msg = "一小时内次数已经达到上限";
                issend = false;
            }
        }else if (jedisUtil.exists(RedisKey_Config.VCODE_FOUR+phone)){
            //一天20次
            int c = Integer.parseInt(jedisUtil.get(RedisKey_Config.VCODE_FOUR+phone));
            if(c >=20){
                msg = "一小时内次数已经达到上限";
                issend = false;
            }
        }
        //以上判断是否可以发短信  说明该手机号码可以发送短信
        //若果可以发送
        boolean isfirst = true;
        int f = 1;
        if(issend){
            //先判断是否已经发送过短信、
            int cd = 0;
            if(jedisUtil.exists(RedisKey_Config.VCODE_CODE+phone)) {
                //若果发送过，从redis里面取出来 然后重新发送短信
                cd = Integer.parseInt(jedisUtil.get(RedisKey_Config.VCODE_CODE + phone));
                isfirst = false;
            }else{
                cd = Random_Util.createNum(6);
            }

              try {
                    JuHeSms_Util.sendMsg(phone,cd);
                    //如果发送成功 更新次数 4个
                  if(isfirst){
                      //验证码5分钟有效
                      jedisUtil.setExpire(RedisKey_Config.VCODE_CODE+phone,cd+"",300);
                  }
                  //设置一分钟
                  setValue(RedisKey_Config.VCODE_FIRST+phone,60);
                  //设置10分钟
                  setValue(RedisKey_Config.VCODE_SECOND+phone,600);
                  //设置一小时
                  setValue(RedisKey_Config.VCODE_THREE+phone,6000);
                  //设置一天
                  setValue(RedisKey_Config.VCODE_FOUR+phone, TimeUtil.getLastSeconds());
                  msg=phone+ "验证码:"+cd;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                  msg=phone+ "验证码:"+cd;
                    f = 2;
              }
            }
            //如果是第一次存 记录日志表
            SmsSend smsSend1 = smsSendMapper.selectByMsg(phone,msg);
            if(smsSend1 == null){
                //第一次发送
                SmsSend smsSend = new SmsSend();
                smsSend.setPhone(phone);
                smsSend.setContent(msg);
                smsSend.setFlag(f);
                smsSend.setIpaddr(ip);
                smsSendMapper.insert(smsSend);
            }else{

                    //验证码不是第一次发送  重发表
                    SmsRestset smsRestset = new SmsRestset();
                    smsRestset.setFlag(f);
                    smsRestset.setSid(smsSend1.getId());
                    smsRestsetMapper.insert(smsRestset);

                }

            MsgLog msgLog = new MsgLog();
            msgLog.setContent(msg);
            msgLog.setType(MsgLogType.validatecode.getIndex());
            msgLogMapper.insert(msgLog);


        //数据库记录

        return R.setR(f==1,"验证码发送成功",null);
    }

    private void setValue(String key,Integer seconds){
        if (jedisUtil.exists(key)){
            jedisUtil.set(key,(Integer.parseInt(jedisUtil.get(key)+1)+""));
        }else {
            //第一次设置有效期
            System.out.println(key+"---->"+seconds);
            jedisUtil.setExpire(key,1+"",seconds);
        }
    }
}
