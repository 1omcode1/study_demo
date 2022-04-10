package com.example.demo.controller;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpHead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LogControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private HttpHeaders headers;


    @BeforeEach  // 每次加载Test Case 之前都会执行的方法
    public void init(){

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();


    }

    @Test
    void getA() {
    }

    @Test
    void testBoolSearch() {
    }

    @Test
    void getHitData() {
    }

    @Test
    void getAggsSearch() {
    }
}