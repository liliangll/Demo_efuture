package com.efuture.demo.mapper;

import com.efuture.demo.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MenuMapper {
    /**
     * 查找用户的菜单
     * @return
     */
    List<Menu> queryMenuList();
}
