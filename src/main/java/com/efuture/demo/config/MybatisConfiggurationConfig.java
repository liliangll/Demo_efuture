package com.efuture.demo.config;
//配置mybatis的分页插件pageHelper

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfiggurationConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        /**该参数默认为false设置为true时会将RowBounds第一个参数offset当成pageNum页码使用和startPage中的pageNum效果一样*/
        properties.setProperty("offsetAsPageNum", "true");
        /**该参数默认为false设置为true时，使用RowBounds分页会进行count查询*/
        properties.setProperty("rowBoundsWithCount", "true");
        /**分页合理化*/
        properties.setProperty("reasonable", "false");
        /**配置mysql数据库的方言*/
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}