package com.hh.controller;

import com.hh.Exception.CustomException;
import com.hh.entity.ActiveUser;
import com.hh.util.Md5Util;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
    public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
        logger.error("--------- i am login ");
        String userName =  request.getParameter("username");
        String password =  request.getParameter("password");
        String rememberMeParam =  request.getParameter("rememberMe");
        boolean isRemberMe =true;
        String randomcode = request.getParameter("randomcode");
        String validateCode = (String) request.getSession().getAttribute("verifyCode");
        logger.error("----------userName:"+userName);
        logger.error("----------password:"+password);
        logger.error("----------rememberMeParam:"+rememberMeParam);
        logger.error("----------randomcode:"+randomcode);
        logger.error("----------validateCode:"+validateCode);
        if(!randomcode.equals(validateCode)){
            throw  new CustomException("验证码错误");
        }

        Subject user = SecurityUtils.getSubject();
        logger.error("------:"+Md5Util.md5Encode(password));
          UsernamePasswordToken token = new UsernamePasswordToken(userName,Md5Util.md5Encode(password));
        token.setRememberMe(true);
        try {
            user.login(token);
            System.out.println("----------:" + user.getPrincipal());
            logger.error("----是否认证："+user.isAuthenticated());
            ActiveUser activeUser=(ActiveUser)user.getPrincipal();
            System.out.println("-------:"+activeUser);
            Boolean hasRole = user.hasRole("商品管理员");

            logger.error("-----单个角色认证："+hasRole);
        } catch ( UnknownAccountException e ) {
            throw new CustomException("账号不存在！msg:"+e.getMessage());
        } catch ( IncorrectCredentialsException e ) {
            throw new CustomException("用户名或密码错误！msg:"+e.getMessage());
        } catch (LockedAccountException e ) {
            throw new CustomException("账号已锁定！msg:"+e.getMessage());
        } catch (ExcessiveAttemptsException e ) {
            throw new CustomException("登录失败次数过多！msg:"+e.getMessage());
        }catch (ExpiredCredentialsException e){
            throw new CustomException("过期的凭证！msg:"+e.getMessage());
        }catch(Exception e){
            throw new CustomException("账号或密码错误！msg:"+e.getMessage());
        }
        // DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）
        // ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等
        //验证是否登录成功
        if(user.isAuthenticated()){
            System.out.println("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        }else{
            token.clear();
        }
        System.out.println("---------------------login success");

     /*   //如果登录失败从request中获取认证异常信息，shrioLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        System.out.println("-----------exceptionClassName:"+exceptionClassName);
        if(exceptionClassName!=null){
            if(UnknownAccountException.class.getName().equals(exceptionClassName)){
                throw new CustomException("账户不存在！");
            }else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                throw new CustomException("账号或密码错误！");
            }else if(exceptionClassName.equals("randomCodeError")){
                throw new CustomException("验证码错误！");
            }else{
                throw  new CustomException("未知错误!");
            }
        }*/
      /*  可以使用配置文件登录成功跳转页面 但是现在没用*/
        return "redirect:/first";
    }

    @RequestMapping("logout")
    public String loginOut(){
      Subject subject = SecurityUtils.getSubject();
      subject.logout();
      return "redirect:/";
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
