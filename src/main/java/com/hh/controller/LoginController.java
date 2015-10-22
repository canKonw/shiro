package com.hh.controller;

import com.hh.Exception.CustomException;
import com.hh.entity.ActiveUser;
import com.hh.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hh on 15-10-21.
 */
@Controller
@RequestMapping("loginController")
public class LoginController {
    @Autowired
    private SysService sysService;
    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request,HttpServletResponse response,String  username,String password,String randomcode) throws Exception{
        String validateCode = (String)request.getAttribute("validateCode");
        if(!validateCode.equals(randomcode)){
           throw  new CustomException("验证码错误！");
        }
        ActiveUser activeUser = sysService.authenticat(username,password);
        request.setAttribute("activeUser", activeUser);
        model.addAttribute("activeUser",activeUser);
        //return "first";
        return "redirect:/first";
    }

    @RequestMapping("/getUser")
    public ModelAndView getUser(HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("login");
    }
}
