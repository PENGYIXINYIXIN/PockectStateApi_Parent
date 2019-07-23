package com.pockectstate.api.pockectstateapi_serverserach.service.impl;

import com.netflix.discovery.converters.Auto;
import com.pockectstate.api.common.config.ESIndex_Config;
import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_serverserach.dao.GoodsDao;
import com.pockectstate.api.pockectstateapi_serverserach.dao.SearchWorldDao;
import com.pockectstate.api.pockectstateapi_serverserach.model.Goods;
import com.pockectstate.api.pockectstateapi_serverserach.model.SearchWorld;
import com.pockectstate.api.pockectstateapi_serverserach.service.GoodsService;
import com.thoughtworks.xstream.core.ReferenceByIdMarshaller;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SearchWorldDao worldDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public R save(Goods goods) {
        goodsDao.save(goods);
        return R.setR(goodsDao.save(goods)!=null,"ok",null);
    }

    @Override
    public R update(Goods goods) {
        goodsDao.save(goods);
        return R.setR(goodsDao.save(goods)!=null,"ok",null);
    }

    @Override
    public R delect(String id) {
        goodsDao.deleteById(id);
        return R.setOK("ok",null);
    }

    @Override
    public R saveall(List<Goods> list) {

        goodsDao.saveAll(list);
        return R.setOK("ok",null);
    }

    @Override
    public R savebatch(Map<String,String > map ) {
        List<IndexQuery> queries = new ArrayList<>();
        for(String k :map.keySet()){
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(k);
            indexQuery.setIndexName(ESIndex_Config.PSGOODS_INDEX);
            indexQuery.setType(ESIndex_Config.PSGOODS_TYPE);
            indexQuery.setVersion(1l);
            indexQuery.setSource(map.get(k));
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);

        return R.setOK("ok",null);
    }

    @Override
    public R searchLike(String msg,String ip) {
        if (msg != null && msg.length()>0) {
            msg = "*"+msg+"*";
            WildcardQueryBuilder w1 = QueryBuilders.wildcardQuery("name",msg);
            WildcardQueryBuilder w2 = QueryBuilders.wildcardQuery("typename",msg);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(w1);
            boolQueryBuilder.should(w2);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            Iterable<Goods> search = goodsDao.search(searchSourceBuilder.query());
            SearchWorld searchWorld = new SearchWorld();
            searchWorld.setId(idGenerator.nextId()+"");
            searchWorld.setWorld(msg);
            searchWorld.setIpaddr(ip);
            worldDao.save(searchWorld);
            return R.setOK("ok",search);
        }else {
          return null;
        }

    }
}
