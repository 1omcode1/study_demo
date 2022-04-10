package com.example.demo.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 分页vo
 */
@Data
public class PageVo implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 页号
     */
    private int pageNumber;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式 asc/desc
     */
    private String order;
}
