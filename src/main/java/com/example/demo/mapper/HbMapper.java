package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 字典分类 Mapper 接口
 * </p>
 *
 * @author zer
 * @since 2021-03-09
 */
public interface HbMapper extends BaseMapper<User> {

    /**
     * 获取表空间中的所有表
     */
    @Select("select TABLE_NAME from dba_tables where TABLESPACE_NAME=#{tableSpaceName} AND TABLE_NAME LIKE 'HB%' ORDER BY TABLE_NAME ")
    List<String> getAllTables(@Param("tableSpaceName") String tableSpaceName);

    @Select("select TABLE_NAME, COLUMN_NAME,DATA_TYPE,DATA_LENGTH from user_tab_cols where TABLE_NAME = #{tableName}")
    List<JSONObject> getAllColumns(@Param("tableName") String tableName);


}
