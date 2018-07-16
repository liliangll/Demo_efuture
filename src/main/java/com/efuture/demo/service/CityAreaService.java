package com.efuture.demo.service;

import com.efuture.demo.mapper.CityAreaMapper;
import com.efuture.demo.model.CityArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author ASUS
 */
@Service
public class CityAreaService {
    @Autowired
    private CityAreaMapper cityAreaMapper;

    public void setCityAreaMapper(CityAreaMapper cityAreaMapper) {
        this.cityAreaMapper = cityAreaMapper;
    }


    public List<CityArea> getAllCityAreas() {
        return cityAreaMapper.getAllCityAreas();
    }

}
