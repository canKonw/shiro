package com.hh.interceptor;

import com.hh.util.ResourcesUtil;
import com.hh.entity.ActiveUser;
import com.hh.entity.SysPermission;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 15-10-21.
 * 系统权限拦截   一定要继承HandlerInterceptor
 */
public class SysPermissionInterceptor implements HandlerInterceptor {
  private Logger logger = Logger.getLogger(SysPermissionInterceptor.class);
    @Override
    //在执行handler之前来执行的
    //用于用户认证校验、用户权限校验
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
       String url   =request.getRequestURI();
        logger.error("-----------SysPermissionInterceptorpre------------Handle");
        /*************************************************匿名地址**********************************************/
        //获得配置中的匿名url
        List<String> open_urls = ResourcesUtil.getkeyList("config/anonymousURL");
        for(String s:open_urls){
            if(url.indexOf(s)>0){
                return true;//存在匿名地址  放行
            }
        }

        /*************************************************公用地址***********************************************/
        //从配置文件中获取公共访问地址
        List<String> common_urls = ResourcesUtil.getkeyList("config/commonURL");
        for(String s :common_urls){
            if(url.indexOf(s)>0){
                return true;//包含公共地址  放行
            }
        }
        /*************************************************获取session***********************************************/
        HttpSession session =request.getSession();
        ActiveUser activeUser = (ActiveUser)session.getAttribute("activeUser");
        List<SysPermission> sysPermissionList = activeUser.getPermissions();
        logger.error("---sysPermissionList:"+sysPermissionList.size());
        for(SysPermission sysPermission:sysPermissionList){
            String permisssion_url = sysPermission.getUrl();
            if(url.endsWith(".css") || url.endsWith(".jpg") || url.endsWith(".js")){
                return  true;
            }else{
                if(url.indexOf(permisssion_url)>0){
                    return  true;//包含权限 放行
                }
            }
        }
        //走到了这里
        //执行到这里拦截，跳转到登陆页面，用户进行身份认证
        request.getRequestDispatcher("/pages/jsp/refuse.jsp").forward(request, response);
        //如果返回false表示拦截不继续执行handler，如果返回true表示放行
        return false;
    }

    /**
     * 在执行handler返回modelAndView之前来执行
     * 如果需要向页面提供一些公用 的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
     * @param request
     * @param response
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
           logger.error("-----------SysPermissionInterceptorpre------------postHandle");
    }

    /**
     * 执行handler之后执行此方法
     * 作系统 统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
     * 实现 系统 统一日志记录
     * @param request
     * @param response
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        logger.error("-----------SysPermissionInterceptorpre------------afterCompletion");

    }
}
