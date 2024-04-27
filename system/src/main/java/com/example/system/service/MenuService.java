package com.example.system.service;

import com.example.system.model.Menu;
import java.util.List;

public interface MenuService{

    List<Menu> getAllMenu();

    Menu getMenuById(Long menuId);

    int insertMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenuById(Long menuId);

    List<Menu> getUserAndListMenu();

    List<Menu> selectSubMenuByMenuId(Long menuId);

    List<Menu> selectSameAndUpperLevelMenu(Long menuId);

}
