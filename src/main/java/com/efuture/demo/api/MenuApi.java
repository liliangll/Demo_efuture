package com.efuture.demo.api;

import com.efuture.demo.annotation.LoginRequired;
import com.efuture.demo.model.Menu;
import com.efuture.demo.service.MenuService;
import com.efuture.demo.util.JsonResponse;
import com.efuture.demo.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/menu")
public class MenuApi {

    @Autowired
    private MenuService menuService;

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value="查询",notes = "查询菜单信息")
    @LoginRequired
    @GetMapping(value = "/listMenu")
    public JsonResponse listMenu() {
        List<Menu> rootMenus = menuService.queryMenuList();
        List<Menu> list = getMenuList(rootMenus);
        return JsonResponse.ok(list);
    }

    /**
     * 找出一级菜单和二级菜单
     * @param rootMenus
     * @return
     */
    private List<Menu> getMenuList(List<Menu> rootMenus){
        List<Menu> list = new ArrayList<Menu>();
        // 先找到所有的一级菜单
        for (int i=0;i<rootMenus.size();i++){
            Menu menu = rootMenus.get(i);
            //一级菜单  没有父节点
            if (StringUtils.isEmpty(menu.getParentId())){
                list.add(menu);
            }
        }
        //查找二级菜单
        /**
         * 利用递归找出所有子菜单
         */
        for (Menu menu: list) {
            menu.setChildMenus(getChild(menu.getId(),rootMenus));
        }
        return list;
    }

    /**
     * 找出子菜单
     * @param id
     * @param rootMenu
     * @return
     */
    private List<Menu> getChild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (!StringUtils.isEmpty(menu.getParentId())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isEmpty(menu.getUrl())) {
                // 递归
                menu.setChildMenus(getChild(menu.getId(), rootMenu));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
