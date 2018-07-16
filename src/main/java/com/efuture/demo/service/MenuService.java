package com.efuture.demo.service;

import com.efuture.demo.mapper.MenuMapper;
import com.efuture.demo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<Menu> queryMenuList() {
        return menuMapper.queryMenuList();
    }

}
