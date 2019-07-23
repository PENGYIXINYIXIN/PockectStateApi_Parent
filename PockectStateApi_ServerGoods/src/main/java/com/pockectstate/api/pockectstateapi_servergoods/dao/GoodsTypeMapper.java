package com.pockectstate.api.pockectstateapi_servergoods.dao;

import com.pockectstate.entity.goods.GoodsType;
import java.util.List;

public interface GoodsTypeMapper {
    int insert(GoodsType record);

    int insertSelective(GoodsType record);

}