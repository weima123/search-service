package com.search.controller;

import com.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/18
 */
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private ElasticSearchService elasticSearchService;
    @GetMapping
    public void test(){
        elasticSearchService.isIndexExist("hhhh");
    }
}
