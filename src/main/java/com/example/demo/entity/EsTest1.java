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
@Document(indexName = "test2", type = "test2", shards = 1, replicas = 0, refreshInterval = "-1")
public class EsTest1 implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 创建者
     */

    private String a;
    private String b;

    private String c;

    private String d;

}
