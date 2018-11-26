package com.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.service.GitCodeUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : mawei
 * @description : TODO
 * @since : 2018/11/19
 */
@Service
public class GitCodeUserServiceImpl implements GitCodeUserService {

    private static final Logger log = LoggerFactory.getLogger(GitCodeUserServiceImpl.class);

    private static final String gitCodeToken = "token 5a7c01a33061470e4dd3d7bb11d5c6a3ed05ba69";

    private static final String baseGitCodeURL = "https://code.huilianyi.com/api/v1/users/{userName}/repos";

    private static final String splitStr = ",";

    private static final  String gitCodeUserFilePath = "/templates/git_code_user.xlsx";




    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String getGitCodeUserDetail(String userName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", gitCodeToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(baseGitCodeURL, HttpMethod.GET, httpEntity, String.class, userName);
            String resp = responseEntity.getBody();
            return parseGitCodeResp(resp, userName);
        }catch (Exception e){
            return "error";
        }

    }

    @Override
    public String parseGitCodeResp(String resp, String userName) {
        if (StringUtils.isEmpty(resp)) {
            log.info("用户{},信息{}", userName, resp);
            return null;
        }
        JSONArray jsonArray = JSON.parseArray(resp);
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            stb.append(jsonObject.get("name")).append(splitStr);
        }
        return stb.toString();
    }

    @Override
    public List<String> parseGitCodeUserExcel(int sheetNum,int cellNum){
        List<String> userNames = new ArrayList<>();
        try (InputStream inputStream = new ClassPathResource(gitCodeUserFilePath).getInputStream()) {
            XSSFWorkbook  workbook = new XSSFWorkbook(inputStream);
            if (workbook == null){
                throw new RuntimeException("workBook init error");
            }
            Sheet sheet = workbook.getSheetAt(sheetNum);
            Iterator iterator = sheet.rowIterator();
            while (iterator.hasNext()){
                Row row = (Row) iterator.next();
                Cell cell = row.getCell(cellNum);

                userNames.add(cell.getStringCellValue() == null ? "" : cell.getStringCellValue().trim());
            }
        }catch (Exception e){
            log.warn("init inputStream error,exception :{}", ExceptionUtils.getFullStackTrace(e));
        }
        return userNames;
    }

    @Override
    public void getGitCodeUserRepTxt(){
        List<String> userNames = this.parseGitCodeUserExcel(0,1);
        if (CollectionUtils.isEmpty(userNames)){
            return;
        }
        try(PrintWriter printWriter = new PrintWriter(new File("d:\\git_code_user_repository.csv"))) {
            for (String userName : userNames){
                String repository = this.getGitCodeUserDetail(userName);
                printWriter.append(userName).append(" ").append(repository).println();
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
