package com.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.search.service.ElasticSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author : mawei
 * @description : the realization for elasticSearchService
 * @since : 2018/11/18
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    @Autowired
    private TransportClient transportClient;


    @Override
    public Boolean isIndexExist(String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = transportClient.admin().indices().exists(request).actionGet();
        return response.isExists();
    }

    @Override
    public Boolean createIndex(String index) {
        if (this.isIndexExist(index)) {
            log.info("[es createIndex] index {} already exists", index);
            return true;
        }
        CreateIndexResponse createIndexResponse = transportClient.admin().indices().prepareCreate(index)
                .execute().actionGet();
        log.info("[es createIndex] success:{}", createIndexResponse.isAcknowledged());
        return createIndexResponse.isAcknowledged();
    }

    @Override
    public Boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            log.info("[es deleteIndex] index {} does not exist", index);
            return true;
        }
        DeleteIndexResponse deleteIndexResponse = transportClient.admin().indices().prepareDelete(index).execute().actionGet();
        if (deleteIndexResponse.isAcknowledged()) {
            log.info("[es deleteIndex] success ...");
            return true;
        }
        return false;
    }

    @Override
    public boolean isTypeExist(String index, String type) {
        if (!isIndexExist(index)) {
            log.info("[es isTypeExist] index {} does not exist", index);
            return false;
        }
        return transportClient.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet().isExists();
    }

    @Override
    public String insert(JSONObject jsonObject, String index, String type, String id) {
        IndexResponse indexResponse = transportClient.prepareIndex(index, type, id).setSource(jsonObject).get();
        log.info("[es addData] response status {} ,id {}", indexResponse.status(), indexResponse.getId());
        return indexResponse.getId();
    }

    @Override
    public String insert(JSONObject jsonObject, String index, String type) {
        return this.insert(jsonObject, index, type, UUID.randomUUID().toString());
    }

    @Override
    public  String deleteById(String index,String type,String id){
        DeleteResponse deleteResponse = transportClient.prepareDelete(index,type,id).execute().actionGet();
        log.info("[es deleteById] status:{} ,id:{}",deleteResponse.status(),deleteResponse.getId());
        return deleteResponse.getId();
    }

    @Override
    public String updateById(JSONObject jsonObject ,String index,String type,String id){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(jsonObject);
        UpdateResponse updateResponse = transportClient.update(updateRequest).actionGet();
        log.info("[es updateById] status:{} , id:{}",updateResponse.status(),updateResponse.getId());
        return updateResponse.getId();
    }




}
