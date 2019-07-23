package com.pockectstate.api.pockectstateapi_serverserach.dao;

import com.pockectstate.api.pockectstateapi_serverserach.model.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
public interface GoodsDao extends ElasticsearchRepository<Goods,String> {
}
