package com.search.gitcode;

import com.search.SearchServiceTest;
import com.search.service.GitCodeUserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/19
 */
public class GitCodeUserServiceTest extends SearchServiceTest {
    private static final Logger log = LoggerFactory.getLogger(GitCodeUserServiceTest.class);

    @Autowired
    private GitCodeUserService gitCodeUserService;

    @Test
    public void getGitCodeUserDetail(){
       String result = gitCodeUserService.getGitCodeUserDetail("enhui.chen");
    }

    @Test
    public void parseGitCodeUserExcel(){
        gitCodeUserService.parseGitCodeUserExcel(0,1);
    }

    @Test
    public void getGitCodeUserRepTxt(){
        gitCodeUserService.getGitCodeUserRepTxt();
    }
}
