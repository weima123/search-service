package com.search.service;

import java.util.List;
import java.util.Map;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/19
 */
public interface GitCodeUserService {
    /**
     * 获取gitCode用户详情
     * @param userName
     * @return
     */
    String getGitCodeUserDetail(String userName);

    /**
     * 解析项目
     * @param resp
     * @param userName
     * @return
     */
    String parseGitCodeResp(String resp,String userName);

    /**
     * 解析excel获取gitCode用户登录名称
     * @param sheetNum
     * @param cellNum
     * @return
     */
    List<String> parseGitCodeUserExcel(int sheetNum,int cellNum);

    /**
     * 统计gitcode 人员项目分配情况
     */
    void getGitCodeUserRepTxt();
}
