package com.pockectstate.api.pockectstateapi_serverserach.task;

import com.pockectstate.api.common.config.RedisKey_Config;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.pockectstateapi_serverserach.config.RabbitMQConfig;
import com.pockectstate.api.pockectstateapi_serverserach.service.GoodsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@Service
public class GoodsEstask {
    @Autowired
    private GoodsService goodsService;
   // @Autowired
    private JedisUtil jedisUtil = JedisUtil.getInstance();
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "0 0 0/4 * * ?")
    public void tbes(){
        //数据变化
        //新增
        //修改
        //删除
        //从数据库 -- 读取 -- 商品流水-- 发送消息 -- RabbitMQ -- 消费者 -- 获取消息
        //从redis -- 读取三个key Hash 防止数据不一致 或者雪崩 双key机制
        //一个key 有效期 四小时10分钟 另一个key 设置永久有效
        // 依次验证key是否存在
        //发送消息 定时触发 开始执行
        rabbitTemplate.convertAndSend(RabbitMQConfig.queuelog,"ES定时任务开始执行");
        //搬运工 redis数据搬运到ES服务器
        opEs(RedisKey_Config.ESHASHADD);
        opEs(RedisKey_Config.ESHASHUPDATE);
        opEs(RedisKey_Config.ESHASHDEL);
        //发送消息 定时触发， 执行结束
        rabbitTemplate.convertAndSend(RabbitMQConfig.queuelog,"ES定时任务结束执行");

    }

    private void opEs(String key){
        if(jedisUtil.exists(key)){
            Map<String, String> map = jedisUtil.hgetall(key);
            goodsService.savebatch(map);
            jedisUtil.del(key);
            jedisUtil.del(key+":slave");
        }else if (jedisUtil.exists(key+":slave")){

            Map<String, String> map = jedisUtil.hgetall(key);
            goodsService.savebatch(map);
            jedisUtil.del(key+":slave");
        }
    }
}
