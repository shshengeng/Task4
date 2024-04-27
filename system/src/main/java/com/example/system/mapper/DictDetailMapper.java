package com.example.system.mapper;


import com.example.system.model.DictDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictDetailMapper {

    @Select("SELECT * FROM sys_dict_detail where detail_id = #{id}")
    DictDetail selectById(Long id);

    @Select("SELECT * FROM sys_dict_detail")
    List<DictDetail> selectAll();

    @Insert("""
I   INSERT INTO sys_dict_detail (detail_id, dict_id, label, value, dict_sort, create_by, update_by, create_time, update_time)
    VALUES (#{detailId}, 
            #{dictId}, 
            #{label}, 
            #{value}
            #{dictSort}
            #{createBy}, 
            #{updateBy}, 
            #{createTime}, 
            #{updateTime})
    """)
    int insert(DictDetail dictDetail);

    @Update("""
    UPDATE sys_dict_detail
    SET 
        detail_id = #{detailId},
        dict_id = #{dictId}, 
        label = #{label},
        value = #{value},
        dict_sort = #{dictSort},
        create_by = #{createBy}, 
        update_by = #{updateBy}, 
        create_time = #{createTime}, 
        update_time = #{updateTime}
    WHERE detail_id = #{detailId}
    """)
    int update(DictDetail dictDetail);


    @Delete("DELETE FROM sys_dict_detail WHERE detail_id = #{id}")
    int deleteById(Long id);


    @Select("SELECT * FROM sys_dict_detail limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize);
}
