package com.hh.interceptor;

import com.hh.util.ResourcesUtil;
import entity.ActiveUser;
import entity.SysPermission;
import org.apache.log4j.Logger;
import sun.security.util.Resources_de;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 15-10-21.
 * 系统权限拦截
 */
public class SysPermissionInterceptor {
  private Logger logger = Logger.getLogger(SysPermissionInterceptor.class);
 public boolean perHandle(HttpServletRequest request,HttpServletResponse response){
       String url   =request.getRequestURI();
        logger.error("-----url:"+url);

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
        for(SysPermission sysPermission:sysPermissionList){
            String permisssion_url = sysPermission.getUrl();
            logger.error("-----perminssion_url:"+permisssion_url);
            if(url.endsWith(".css") || url.endsWith(".jsg") || url.endsWith(".js")){
                return  true;
            }else{
                if(url.indexOf(permisssion_url)>0){
                    return  true;//包含权限 放行
                }
            }
        }
        //走到了这里

        return  true;
    }
}
