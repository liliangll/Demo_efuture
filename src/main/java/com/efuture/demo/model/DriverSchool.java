package com.efuture.demo.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 驾校表
 */
@Data
public class DriverSchool{
	private Integer did;//驾校ID

	private String dname;//驾校名称

	private String address;//驾校地址

	private String phone;//驾校电话

	private CityArea cityArea;//把城区的对象作为属性




}
