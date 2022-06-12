package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        // 通过getAttribute获取forward中的的参数
        System.out.println(request.getAttribute("name"));
        // 通过getParameterMap获取redirect中的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        // 获取请求域中的参数
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject( "key1","1");
        modelAndView.addObject("key2","2");

        return modelAndView;
    }

    // 重定向redirect
    @GetMapping("/redirect")
    public ModelAndView redirect(Model model){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","dyy");
        mv.addObject("sex","男");
        mv.setViewName("redirect:/index?param=123");
        return mv;
    }

    // forward
    @GetMapping("/forward")
    public ModelAndView forward(Model model){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","dyy");
        mv.setViewName("forward:/index");
        return mv;
    }


    public static Map<String,Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        request.getParameterMap().forEach((k, v) -> {
            if (v != null) {
                if (v.length == 1) {
                    param.put(k, v[0]);
                }else {
                    if (k.endsWith("[]")) {
                        param.put(k.substring(0, k.length() - 2), v);
                    } else {
                        param.put(k, v);
                    }
                }
            }
        });
        return param;
    }
}
