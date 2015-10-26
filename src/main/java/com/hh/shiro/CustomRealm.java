package com.hh.shiro;

import com.hh.entity.ActiveUser;
import com.hh.entity.SysPermission;
import com.hh.entity.SysUser;
import com.hh.entity.User;
import com.hh.service.SysPermissionService;
import com.hh.service.SysUserService;
import com.hh.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.util.logging.resources.logging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by huhao on 15-10-20.
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    Logger logger = Logger.getLogger(CustomRealm.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 进行认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token) throws AuthenticationException {
        logger.error("------- i am doGetAuthenticationInfo");
        String userCode = (String) token.getPrincipal();
        SysUser sysUser = sysUserService.getSysUserByCode(userCode);
        if(sysUser==null){
           return null;
        }
        String passWord = sysUser.getPassword();
        String salt = sysUser.getSalt();
        ActiveUser activeUser =new ActiveUser();
        activeUser.setUserId(sysUser.getId());
        activeUser.setUserCode(sysUser.getUserCode());
        activeUser.setUserName(sysUser.getUserName());
        //获得菜单
        List<SysPermission> menu = sysPermissionService.findMenuListByUserId(sysUser.getId());
        activeUser.setMenus(menu);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                activeUser,passWord, ByteSource.Util.bytes(salt),this.getName());
        return simpleAuthenticationInfo;
    }


    /**
     *     用于授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals) {
        //从 principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();
        //获得所有的权限
        List<SysPermission> permissionList = sysPermissionService.findPermissionListByUserId(activeUser.getUserId());
        List<String> permissions = new ArrayList<String>();
        if(permissionList!=null){
            for(SysPermission sysPermission:permissionList){
                permissions.add(sysPermission.getPerCode());
            }
        }
        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 清除缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
