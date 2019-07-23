package com.pockectstate.api.pockectstateapi_servergoods.dao;

import com.pockectstate.entity.goods.GoodsRepertory;
import java.util.List;

public interface GoodsRepertoryMapper {
    int insert(GoodsRepertory record);

    int insertSelective(GoodsRepertory record);

}