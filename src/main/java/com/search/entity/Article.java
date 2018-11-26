package com.search.entity;


import java.io.Serializable;
import java.util.Date;



/**
 * @author : mawei
 * @description : Article
 * @since : 2018/11/9
 */
public class Article implements Serializable {
    /**id**/
    private Long id;
    /**标题**/
    private String title;
    /**摘要**/
    private String abstracts;
    /**内容**/
    private String content;
    /**发表日期**/
    private Date postDate;
    /**点击率**/
    private Long clickCount;

    public Article() {
    }

    public Article(Long id, String title, String abstracts, String content, Date postDate, Long clickCount) {
        this.id = id;
        this.title = title;
        this.abstracts = abstracts;
        this.content = content;
        this.postDate = postDate;
        this.clickCount = clickCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }
}
