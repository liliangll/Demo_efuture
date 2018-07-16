package com.efuture.demo.mapper;

import com.efuture.demo.model.CityArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface CityAreaMapper {
	List<CityArea> getAllCityAreas();
}
