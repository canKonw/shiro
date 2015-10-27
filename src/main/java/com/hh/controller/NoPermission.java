package com.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 15-10-28.
 * 没有权限跳转的页面
 */
@Controller
@RequestMapping("/")
public class NoPermission {

    @RequestMapping("/noPermission")
    public ModelAndView noPermission(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("refuse");
        return  modelAndView;
    }

}
