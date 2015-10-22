package com.hh.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huhao on 15-10-21.
 * 用户身份信息  存在session里面
 * 有序tomcat会将session信息写到硬盘，所有这里需要序列化
 */
public class ActiveUser implements Serializable {
    private String userId;//用户id（主键）
    private String userCode;// 用户账号
    private String userName;// 用户名称
    private List<SysPermission> menus;// 菜单
    private List<SysPermission> permissions;// 权限

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SysPermission> getMenus() {
        return menus;
    }

    public void setMenus(List<SysPermission> menus) {
        this.menus = menus;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
