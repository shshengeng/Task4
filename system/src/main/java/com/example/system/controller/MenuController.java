package com.example.system.controller;


import com.example.common.response.ResponseResult;
import com.example.system.model.Menu;
import com.example.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1、实现 菜单的增删改查功能
 * 2、根据当前用户权限获取前端展示菜单
 * 3、根据菜单id获取所有子菜单id
 * 4、根据菜单id获取同级和上级数据
 */

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/getMenu")
    public ResponseResult<List<Menu>> getAllMenu(){
        List<Menu> allMenu = menuService.getAllMenu();
        if(allMenu.size() != 0){
            return new ResponseResult<List<Menu>>(200, "query menu successfully", allMenu, System.currentTimeMillis());
        }
        return new ResponseResult<List<Menu>>(500, "empty list", null, System.currentTimeMillis());
    }

    @GetMapping("/getMenu/{menuId}")
    public ResponseResult<Menu> getMenuById(@PathVariable Long menuId){
        Menu menu = menuService.getMenuById(menuId);
        if(menu != null){
            return new ResponseResult<Menu>(200, "query menu successfully", menu, System.currentTimeMillis());
        }
        return  new ResponseResult<Menu>(500, "didn't find menu object", null, System.currentTimeMillis());
    }

    @PostMapping("/insertMenu")
    public ResponseResult insertMenu(Menu menu){
        int i = menuService.insertMenu(menu);
        if(i > 0){
            return new ResponseResult(200, "insert menu successfully", null, System.currentTimeMillis());
        }
        return new ResponseResult(500, "insert menu failed", null, System.currentTimeMillis());
    }

    @PutMapping("/updateMenu")
    public ResponseResult updateMenu(Menu menu){
        int i = menuService.updateMenu(menu);
        if(i > 0){
            return new ResponseResult(200, "update menu successfully", null, System.currentTimeMillis());
        }
        return new ResponseResult(500, "update menu failed", null, System.currentTimeMillis());
    }

    @DeleteMapping("/deleteMenu/{menuId}")
    public ResponseResult deleteMenu(@PathVariable Long menuId){
        int i = menuService.deleteMenuById(menuId);
        if(i > 0){
            return new ResponseResult(200, "delete menu successfully", null, System.currentTimeMillis());
        }
        return new ResponseResult(500, "delete menu failed", null, System.currentTimeMillis());
    }


    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping("/menu/authority/user:list")
    public ResponseResult<List<Menu>> getUserAndListMenu() {
        List<Menu> menus = menuService.getUserAndListMenu();
        if(menus.size() != 0){
            return new ResponseResult<List<Menu>>(200, "query user menu successfully", menus, System.currentTimeMillis());
        }
        return new ResponseResult<List<Menu>>(500, "empty list", null, System.currentTimeMillis());

    }


    @GetMapping("/subMenu/{menuId}")
    public ResponseResult<List<Menu>> getSubMenuByMenuId(@PathVariable Long menuId){
        List<Menu> menus = menuService.selectSubMenuByMenuId(menuId);
        if(menus.size() != 0){
            return new ResponseResult<List<Menu>>(200, "query sub menu by menuId successfully", menus, System.currentTimeMillis());
        }
        return new ResponseResult<List<Menu>>(500, "empty list", null, System.currentTimeMillis());

    }


    @GetMapping("/sameAndUpper/{menuId}")
    public ResponseResult<List<Menu>> getSameAndUpperLevelMenu(@PathVariable Long menuId){
        List<Menu> menus = menuService.selectSameAndUpperLevelMenu(menuId);
        if(menus.size() != 0){
            return new ResponseResult<List<Menu>>(200, "query same and upper level menu successfully", menus, System.currentTimeMillis());
        }
        return new ResponseResult<List<Menu>>(500, "empty list", null, System.currentTimeMillis());

    }

}
