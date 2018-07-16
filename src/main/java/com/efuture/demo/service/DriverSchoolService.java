package com.efuture.demo.service;

import com.efuture.demo.mapper.DriverSchoolMapper;
import com.efuture.demo.model.DriverSchool;
import com.efuture.demo.util.ExcelUtil;
import com.efuture.demo.util.ResultObjStr;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 *
 */
@Service
public class DriverSchoolService{
	@Autowired
	private DriverSchoolMapper driverSchoolMapper;

	public void setDriverSchoolMapper(DriverSchoolMapper driverSchoolMapper) {
		this.driverSchoolMapper = driverSchoolMapper;
	}

	public int insert(DriverSchool school) {
		return this.driverSchoolMapper.insert(school);
	}

	public int del(int did) {
		return this.driverSchoolMapper.del(did);
	}

	public DriverSchool getDetail(int did) {
		return this.driverSchoolMapper.getDetail(did);
	}

	public int update(DriverSchool school) {
		return this.driverSchoolMapper.update(school);
	}


	public List<DriverSchool> findAllUser(int pageNum, int pageSize) {
		//将参数传给这个方法就可以实现物理分页了，非常简单。
		PageHelper.startPage(pageNum, pageSize);
		return driverSchoolMapper.selectAllUser();
	}


	public List<DriverSchool> selectDname(String dname) {
		return driverSchoolMapper.selectDname(dname);
	}




}
