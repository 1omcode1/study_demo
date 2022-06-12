package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典分类 Mapper 接口
 * </p>
 *
 * @author zer
 * @since 2021-03-09
 */

public interface OtherMapper extends BaseMapper<User> {

    /**
     * 获取表空间中的所有表
     */
    @DS("db")
    List<String> getAllTables(@Param("tableSpaceName") String tableSpaceName);

    @DS("db")
    List<JSONObject> getAllColumns(@Param("tableName") String tableName);
}
