package com.pockectstate.api.pockectstateapi_servergoods.dao;

import com.pockectstate.entity.goods.AttributeValue;

import java.util.List;

public interface AttributeValueMapper {
    int insert(AttributeValue record);

    int insertSelective(AttributeValue record);

}