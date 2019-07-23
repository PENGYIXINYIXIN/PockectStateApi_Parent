package com.pockectstate.api.pockectstateapi_servergoods.dao;


import com.pockectstate.entity.goods.Goods;

import java.util.List;

public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);

}