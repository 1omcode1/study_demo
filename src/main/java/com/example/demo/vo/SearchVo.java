package com.example.demo.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 前端查询的vo
 */
@Data
public class SearchVo implements Serializable {

    /**
     * 起始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}
