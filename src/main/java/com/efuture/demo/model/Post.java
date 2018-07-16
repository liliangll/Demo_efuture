package com.efuture.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * 文章类
 */
@Data
public class Post {
    private Integer id; //文章Id
    private User author;
    private Integer authorId;    // 作者的 id
    private String title;        //文章标题
    private String content;      //文章内容
    private Date createTime; //时间

}
