package com.pockectstate.api.pockectstateapi_servergoods.dao;

import com.pockectstate.entity.goods.GoodsSku;
import java.util.List;

public interface GoodsSkuMapper {
    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);
}