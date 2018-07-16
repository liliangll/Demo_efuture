package com.efuture.demo.model;

import lombok.Data;

@Data
public class User {
    private Integer id; // 这里不用 int， 应为 int 自动初始化为0，mybatis mapper 文件 就不能使用 <if test="id!=null"> 了
    private String name; //用户名
    private String password;//密码

}
