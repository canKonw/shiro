package com.hh.controller;

import com.hh.Exception.CustomException;
import com.hh.util.Md5Util;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用shiro进行登录
 * Created by Administrator on 15-10-26.
 */
@Controller
@RequestMapping("/")
public class ShiroLoginController {
    private Logger logger =Logger.getLogger(ShiroLoginController.class);
    @RequestMapping("/login")
    public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
        System.out.println("-------------- iam here");
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("ycy", Md5Util.md5Encode("123456"));
        user.login(token);
        System.out.println("---------------------");
        //如果登录失败从request中获取认证异常信息，shrioLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        if(exceptionClassName!=null){
            if(UnknownAccountException.class.getName().equals(exceptionClassName)){
                throw new CustomException("账户不存在！");
            }else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                throw new CustomException("账号或密码错误！");
            }else{
                throw  new CustomException("未知错误!");
            }
        }
    }

    @RequestMapping("/toLogin")
    public ModelAndView getUser(HttpServletRequest request,HttpServletResponse response){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()
                +":"+request.getServerPort()+"/";
        logger.error("---------:"+path);
        logger.error("---------:"+basePath);
        return new ModelAndView("login");
    }
}
