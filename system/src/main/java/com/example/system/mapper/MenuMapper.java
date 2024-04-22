package com.example.system.mapper;


import com.example.system.model.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("SELECT * FROM sys_menu")
    List<Menu> getAllMenu();

    @Select("SELECT * FROM sys_menu WHERE menu_id = #{menuId}")
    Menu getMenuById(Long menuId);

    @Insert("""
    INSERT INTO sys_menu
    VALUES(#{menId}, #{pid}, #{subCount}, #{type}, #{title}, #{name}, #{component},
    #{menuSort}, #{icon}, #{path}, #{iFrame}, #{cache}, #{hidden}, #{permission},
    #{createBy}, #{updateBy}, #{createTime}, #{updateTime})
    """)
    int insertMenu(Menu menu);


    @Update("""
    UPDATE sys_menu
    SET pid = #{pid},
        sub_count = #{subCount},
        type = #{type},
        title = #{title},
        name = #{name},
        component = #{component},
        menu_sort = #{menuSort},
        icon = #{icon},
        path = #{path},
        i_frame = #{iFrame},
        cache = #{cache},
        hidden = #{hidden},
        permission = #{permission},
        create_by = #{createBy},
        update_by = #{updateBy},
        create_time = #{createTime},
        update_time = #{updateTime}
    WHERE men_id = #{menId}
    """)
    int updateMenu(Menu menu);


    @Delete("DELETE FROM sys_menu WHERE men_id = #{menId}")
    int deleteMenuById(Long menuId);


    @Select("SELECT * FROM sys_menu WHERE permission=user:list")
    List<Menu> getUserAndListMenu();


    @Select("SELECT * FROM sys_menu WHERE pid = #{menuId}")
    List<Menu> selectSubMenuByMenuId(Long menuId);


    @Select("SELECT * FROM sys_menu WHERE menu_id = #{menuId} AS a, SELECT * FROM sys_menu WHERE pid = (SELECT menu_id FROM sys_menu WHERE menu_id = #{menuId}) AS b")
    List<Menu> selectSameAndUpperLevelMenu(Long menuId);
}
