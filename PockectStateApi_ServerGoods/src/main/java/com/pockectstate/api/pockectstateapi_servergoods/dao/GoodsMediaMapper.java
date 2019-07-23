package com.pockectstate.api.pockectstateapi_servergoods.dao;

import com.pockectstate.entity.goods.GoodsMedia;
import java.util.List;

public interface GoodsMediaMapper {
    int insert(GoodsMedia record);

    int insertSelective(GoodsMedia record);
}