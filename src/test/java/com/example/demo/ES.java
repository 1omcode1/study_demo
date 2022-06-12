/*
package com.example.demo;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class ES {


    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
    }

    */
/**
     * 1.matchAll
     *//*

    @Test
    void test01() throws IOException {

        //构建查询请求对象，指定查询的索引名称
        SearchRequest searchRequest = new SearchRequest("goods");

        //构建条件构建器：SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //构建查询条件 ∈（org.elasticsearch.index.query)
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        //指定查询条件和分页条件
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.query(matchAllQueryBuilder);

        //查询条件构建器
        searchRequest.source(sourceBuilder);

        //查询、获取结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //获取命中对象hits
        SearchHits hits = searchResponse.getHits();

        //获取命中总数
        TotalHits totalHits = hits.getTotalHits();
        System.out.println("总记录数："+ totalHits);

        //获取单个hit
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            //获取hit详细数据-JSON字符串类型
            String sourceAsString = searchHit.getSourceAsString();
            System.out.println("sourceAsString："+sourceAsString);

            //获取hit详细数据-JSON类型
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            System.out.println("sourceAsMap："+sourceAsMap);
        }


    }

    */
/**
     * match词条查询
     * @throws IOException
     *//*

    @Test
    void test02() throws IOException {
        //构建查询请求对象，指定查询的索引名称
        SearchRequest searchRequest = new SearchRequest("goods");

        //构建条件构建器：SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //构建查询条件 ∈（org.elasticsearch.index.query)
        //注意：这里如果使用match进行搜索，如果title是text类型，意味着可以进行分词搜索，按照默认分词标准，会将“华为手”分成“华”、“为”、“手”三个字分别搜索
        //默认取并集(OR)，所以最后获取到的结果是全部;
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "华为手");
        //如果取交集，则会得到按照三个字分别查询出结果的相同值
        matchQueryBuilder.operator(Operator.AND);

       */
/* //如果是“华为”，则查询出的结果只有一个，因为es中符合“华”、“为”的只有一个
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "华为");*//*


        //指定查询条件和分页条件
        sourceBuilder.query(matchQueryBuilder);

        //查询条件构建器
        searchRequest.source(sourceBuilder);

        //查询、获取结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //获取命中对象hits
        SearchHits hits = searchResponse.getHits();

        //获取命中总数
        TotalHits totalHits = hits.getTotalHits();
        System.out.println("总记录数："+ totalHits);

        //获取单个hit
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            //获取hit详细数据-JSON字符串类型
            String sourceAsString = searchHit.getSourceAsString();
            //System.out.println("sourceAsString："+sourceAsString);
            //获取hit详细数据-JSON类型
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            System.out.println("sourceAsMap："+sourceAsMap);
        }
    }



}
*/
