package com.example.demo.entity;

import com.example.demo.utils.ObjectUtil;
import com.example.demo.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * logs实体
 */
@Data
@Document(indexName = "log", type = "log", shards = 1, replicas = 0, refreshInterval = "-1")
public class EsLog implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private String id = SnowFlakeUtil.nextId().toString();
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date, index = false, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();
    /**
     * 时间戳 查询时间范围时使用
     */
    private Long timeMillis = System.currentTimeMillis();
    /**
     * 方法操作名称
     */
    private String name;
    /**
     * 日志类型
     */
    private Integer logType;
    /**
     * 请求链接
     */
    private String requestUrl;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 请求用户
     */
    private String username;
    /**
     * ip
     */
    private String ip;
    /**
     * 花费时间
     */
    private Integer costTime;
    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        this.requestParam = ObjectUtil.mapToString(paramMap);
    }
}
