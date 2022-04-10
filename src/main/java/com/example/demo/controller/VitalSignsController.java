package com.example.demo.controller;

import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sign")
public class VitalSignsController {

    @PostMapping("/index")
    @ResponseBody
    public Result<Object> index() {
        return ResultUtil.success("测试成功");
    }

    // 跳转页面：方法一ModelAndView
    @GetMapping("/index1")
    public ModelAndView toPageMethod1() {
        // 获取请求域中的参数
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    // 跳转页面：方法二
    @GetMapping("/index2")
    public String toPageMethod2() {
        return "page";
    }

    // 跳转页面：方法三(打开localhost:8889，默认跳转到index页面)
    /* 使用config文件夹下的SpringMvcConfig配置类，实现WebMvcConfigurer接口，重写addViewControllers方法
     * 并且注册成容器
     * */


}
