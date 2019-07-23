package com.pockectstate.api.pockectstateapi_serverserach.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_serverserach.model.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
public interface GoodsService {

    //新增
    R save(Goods goods);
     //* 修改
    R update(Goods goods);
     //* 删除
    R delect(String id);
     //* 批量新增
    R saveall(List<Goods> list);
    R savebatch(Map<String,String > map);
     //* 查询模糊
    R searchLike(String msg,String ip);


}
