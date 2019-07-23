package com.pockectstate.api.pockectstateapi_servergoods.service;

import com.pockectstate.api.common.vo.R;

/**
 *@Author feri
 *@Date Created in 2019/7/16 16:53
 */
public interface GoodsService {
    R queryDetail(int gid);
    R queryHot();//热门商品
    R queryLike(String token);//根据用户的浏览排名
    R querypage();



}
