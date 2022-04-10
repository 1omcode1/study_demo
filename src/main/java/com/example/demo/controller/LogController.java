package com.example.demo.controller;

import com.example.demo.annotation.SystemLog;
import com.example.demo.entity.EsLog;
import com.example.demo.enums.LogType;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtil;
import com.example.demo.vo.PageVo;
import com.example.demo.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 日志操作controller
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {


    @Autowired
    private RestHighLevelClient client;

    /**
     * 测试
     */
    @SystemLog(description = "测试", type = LogType.OPERATION)
    @RequestMapping(value = "/getA", method = RequestMethod.GET)
    public Result<Object> getA(String va) {
        return ResultUtil.success("测试成功");
    }



    /**
     * 布尔查询
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/test/bool", method = RequestMethod.GET)
    public Result<Object> testBoolSearch() throws IOException {

        //条件搜索
        //第一步：使用SearchRequest 构建搜索请求，并指定索引
        SearchRequest request = new SearchRequest("test1");

        //第二步：使用sourceBuilder 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //第三步：编写搜索条件，使用QueryBuilders.boolQuery()，相当于布尔查询条件最外层的那个‘bool’

        //条件一
        BoolQueryBuilder filterCaseBuilder = QueryBuilders.boolQuery();
        //使用两次must，意味着这两个条件在同一个must中，也就是must（and)的关系
        //(a=1 and b=2)
        filterCaseBuilder.must(QueryBuilders.termQuery("a", "1"));
        filterCaseBuilder.must(QueryBuilders.termQuery("b", "2"));

        //条件二
        BoolQueryBuilder filterPhoneBuilder = QueryBuilders.boolQuery();
        //(c=1 or c=3)
        filterPhoneBuilder.should(QueryBuilders.termQuery("c", "1"));
        filterPhoneBuilder.should(QueryBuilders.termQuery("c", "3"));

        //将条件一和条件二合成一个复合条件
        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        //这里的must就是指上面两个条件之间是and的关系
        //(a=1 and b=2) and (c=1 or c=3)
        filterBuilder.must(filterCaseBuilder).must(filterPhoneBuilder);

        //聚合查询条件
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("a")
                .field("company.keyword");
        aggregation.subAggregation(AggregationBuilders.avg("average_age")
                .field("age"));
        sourceBuilder.aggregation(aggregation);

        //第四步：将搜索条件放入sourceBuilder内
        sourceBuilder.query(filterCaseBuilder);

        //第五步：将sourceBuilder放入SearchRequest内
        request.source(sourceBuilder);

        //第六步：使用client调用request请求，进行数据搜索
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        //获取searchResponse中hits中的数据
        this.getHitData(searchResponse);

        //获取searchResponse中聚合函数aggs中的数据

        return ResultUtil.data(null);
    }

    /**
     * 布尔查询应用
     *
     * @param searchResponse SearchResponse对象
     * @return
     */
    public Result<Object> getHitData(SearchResponse searchResponse) {

        //第一步：从searchResponse中获取所有的SearchHits
        SearchHits hits = searchResponse.getHits();
        //然后可以获取所有的hits数量（其实就是文档数量，同时作为搜索出的数据总量）
        TotalHits totalHits = hits.getTotalHits();
        long numHits = totalHits.value;
        System.out.println("numHitsL:" + numHits);

        //Nested（嵌套的） inside the SearchHits are the individual search results that can be iterated over:
        SearchHit[] searchHits = hits.getHits();
        System.out.println(searchHits.length);
        for (SearchHit hit : searchHits) {
            //sourceAsString：返回的文档数据是JSON字符串类型
            String sourceAsString = hit.getSourceAsString();
            System.out.println("'" + hit.getIndex() + "'：" + sourceAsString);

            //sourceAsMap:返回的文档数据是JSON类型
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println("'" + hit.getIndex() + "'：" + sourceAsMap);

            //指定字段高亮显示(存在问题)
/*            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField a = highlightFields.get("a");
            Text[] fragments = a.fragments();
            String s = fragments[0].string();
            System.out.println(s);*/
        }

        return ResultUtil.data(null);
    }


    /**
     * 聚合查询应用
     *
     * @param searchResponse SearchResponse对象
     * @return
     */
    public Result<Object> getAggsSearch(SearchResponse searchResponse) {

        Aggregations aggregations = searchResponse.getAggregations();


        return ResultUtil.data(null);
    }


}
