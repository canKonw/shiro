package com.hh.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

/**
 * Created by huhao on 15-10-20.
 */
public class CustomRealm extends AuthorizingRealm {
    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token) throws AuthenticationException {
        return null;
    }
    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals) {
        return null;
    }
    //清除缓存
    /*public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }*/
}
