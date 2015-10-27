package com.hh.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 15-10-26.
 * 进行表单验证   需要在配置文件中进行这个类的配置
 */
public class FormAuthentication extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        /* System.out.println("----------- i am FormAuthentication");
        // 校验验证码
        // 从session获取正确的验证码
        HttpSession session = ((HttpServletRequest)request).getSession();
        //页面输入的验证码
        String randomcode = request.getParameter("randomcode");

        //从session中取出验证码
        String validateCode = (String) session.getAttribute("validateCode");
        System.out.println("----randomcode:"+randomcode);
        System.out.println("---validateCode:"+validateCode);
       /* if (randomcode!=null&&validateCode!=null&&!randomcode.equals(validateCode)) {
            // randomCodeError表示验证码错误
            request.setAttribute("shiroLoginFailure", "randomCodeError");
            //拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
        */
        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        System.out.println("------ i am FormAuthentication");
        String username = getUsername(request);
        String password = getPassword(request);
        String rememberMeParam = getRememberMeParam();
        System.out.println("----------:"+rememberMeParam);
        if (password == null) {
            password = "";
        }
        boolean rememberMe = true;
        if(rememberMeParam==null || rememberMeParam.equals("0")){
            rememberMe = false;
        }
        String host = getHost(request);
        return new UsernamePasswordToken(username, password, rememberMe, host);
    }
}
