package com.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by mawei on 2017/6/9.
 */
@SpringBootApplication
@ServletComponentScan
public class SearchApplication {

    public static  final Logger log = LoggerFactory.getLogger(SearchApplication.class);


    public static void main(String []args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(SearchApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------"+
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port")
         );

    }
}
