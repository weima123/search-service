package com.search.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : mawei
 * @description : the elasticSearch operation util
 * @since : 2018/11/18
 */
public interface ElasticSearchService {
    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    Boolean isIndexExist(String index);

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    Boolean createIndex(String index);

    /**
     * 删除索引
     * @param index
     * @return
     */
    Boolean deleteIndex(String index);

    /**
     * 判断index X 下 type Y是否存在
     * @param index
     * @param type
     * @return
     */
    boolean isTypeExist(String index, String type);

    /**
     * 数据添加
     * @param jsonObject
     * @param index
     * @param type
     * @param id
     * @return
     */
    String insert(JSONObject jsonObject, String index, String type, String id );

    /**
     * 新增数据
     * @param jsonObject
     * @param index
     * @param type
     * @return
     */
    String insert(JSONObject jsonObject, String index ,String type);

    /**
     * delete by id
     * @param index
     * @param type
     * @param id
     * @return
     */
    String deleteById(String index,String type,String id);

    /**
     * update by id
     * @param jsonObject
     * @param index
     * @param type
     * @param id
     * @return
     */
    String updateById(JSONObject jsonObject ,String index,String type,String id);


}
