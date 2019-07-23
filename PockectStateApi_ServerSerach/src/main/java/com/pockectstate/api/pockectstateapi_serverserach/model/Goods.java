package com.pockectstate.api.pockectstateapi_serverserach.model;

import com.pockectstate.api.common.config.ESIndex_Config;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@Data
@Document(indexName = ESIndex_Config.PSGOODS_INDEX,type = ESIndex_Config.PSGOODS_TYPE)
public class Goods {
    private String id;
    private String name;
    private String typename;
}
