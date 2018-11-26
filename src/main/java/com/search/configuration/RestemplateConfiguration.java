package com.search.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/3/6
 */
@Configuration
public class RestemplateConfiguration {

    @Bean
    public RestTemplate getRestemplate(){
        return new RestTemplate();
    }

}
