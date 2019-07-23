package com.pockectstate.api.pockectstateapi_serverserach.model;

import com.pockectstate.api.common.config.ESIndex_Config;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@Data
@Document(indexName = ESIndex_Config.PSSEARCH_INDEX,type = ESIndex_Config.PSSEARCH_TYPE)
public class SearchWorld {
    private  String id;
    private String world;
    private String ipaddr;
}
