package com.example.system.service.Impl;

import com.example.system.mapper.MenuMapper;
import com.example.system.model.Menu;
import com.example.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public Menu getMenuById(Long menuId) {
        return menuMapper.getMenuById(menuId);
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }


    @Override
    public List<Menu> getUserAndListMenu() {
        return menuMapper.getUserAndListMenu();
    }

    @Override
    public List<Menu> selectSubMenuByMenuId(Long menuId) {
        return menuMapper.selectSubMenuByMenuId(menuId);
    }

    @Override
    public List<Menu> selectSameAndUpperLevelMenu(Long menuId) {
        return menuMapper.selectSameAndUpperLevelMenu(menuId);
    }
}
