package com.efuture.demo.mapper;

import com.efuture.demo.model.DriverSchool;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DriverSchoolMapper {

	List<DriverSchool> selectAllUser();

	public int insert(DriverSchool school);

	public List<DriverSchool> selectDname(String dname);

	public int del(int did);

	public DriverSchool getDetail(int did);

	public int update(DriverSchool school);
}
