package com.search;

import javafx.application.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchApplication.class})
@ActiveProfiles(value = "local")
@WebAppConfiguration
public class SearchServiceTest {
}
