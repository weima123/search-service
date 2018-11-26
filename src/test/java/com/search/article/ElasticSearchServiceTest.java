package com.search.article;

import com.search.SearchServiceTest;
import com.search.entity.Article;
import com.search.service.ElasticSearchService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/15
 */
public class ElasticSearchServiceTest extends SearchServiceTest {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    public void isIndexExist(){
        elasticSearchService.isIndexExist("hhhh");
    }

    @Test
    public void createIndex(){
        elasticSearchService.createIndex("hhhh");
    }

    @Test
    public void deleteIndex(){
        elasticSearchService.deleteIndex("hhhh");
    }

}
