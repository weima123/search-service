package com.search.configuration;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/14
 */
@Configuration
public class EsConfig {
    private static final Logger log = LoggerFactory.getLogger(EsConfig.class);
    /**
     * es服务器地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;
    /**
     * es服务器端口
     */
    @Value("${elasticsearch.port}")
    private Integer port;
    /**
     * es集群名称
     */
    @Value("${elasticsearch.clusterName}")
    private String clusterName;
    /**
     * 链接池
     */
    @Value("${elasticsearch.poolSize}")
    private Integer poolSize;
    @Value("${elasticsearch.userName}")
    private String userName;
    @Value("${elasticsearch.passWord}")
    private String passWord;

    @Bean
    public TransportClient getEsClient(){
        log.debug("[init es start ... ] ");
        Settings esSettings = getEsSettings();
        try  {
            TransportClient transportClient = new PreBuiltTransportClient(esSettings);
            //创建es节点
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), port);
            transportClient.addTransportAddress(transportAddress);
            log.debug("[init es success ...]");
            return transportClient;
        }catch (Exception e){
            log.warn("[init es failed ...] exception:{}", ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }



    private Settings getEsSettings(){
       return Settings.builder()
                .put("cluster.name",clusterName) //集群名称
                .put("client.transport.sniff",false) //寻找集群,嗅探机制开启
                .put("thread_pool.search.size",poolSize) //增加线程池个数
                .build();
    }





}
