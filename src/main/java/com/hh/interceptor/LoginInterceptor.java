package com.hh.interceptor;

import com.hh.entity.ActiveUser;
import com.hh.util.ResourcesUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.util.logging.resources.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 15-10-22.
 * 用户登录情款拦截  主要是判断用户是否登录
 */
public class LoginInterceptor  implements HandlerInterceptor{
    private Logger logger = Logger.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.error("-----------loginInterceptor---preHandle");
        String url = request.getRequestURI();
        List<String> open_urls = ResourcesUtil.getkeyList("config/anonymousURL");
        //遍历公开 地址，如果是公开 地址则放行
        for(String open_url:open_urls){
            if(url.indexOf(open_url)>=0){
                //如果是公开 地址则放行
                return true;
            }
        }
        HttpSession session = request.getSession();
        ActiveUser activeUser = (ActiveUser)session.getAttribute("activeUser");
        if(activeUser!=null){
            return true;
        }
        //没有登录，跳转至登录页面
        request.getRequestDispatcher("/pages/jsp/login.jsp").forward(request, response);
        //如果返回false表示拦截不继续执行handler，如果返回true表示放行
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        logger.error("-----------loginInterceptor---postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        logger.error("-----------loginInterceptor---afterHandle");
    }
}
