package com.hh.service;

import com.hh.Exception.CustomException;
import com.hh.dao.SysPermissionDao;
import com.hh.dao.SysUserDao;
import com.hh.util.Md5Util;
import com.hh.entity.ActiveUser;
import com.hh.entity.SysPermission;
import com.hh.entity.SysUser;
import com.hh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huhao on 15-10-20.
 */
@Service
public class SysService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    public ActiveUser authenticat(String userCode,String pwd) throws Exception{
           SysUser sysUser = sysUserDao.getSysUserByCode(userCode);
            if(sysUser==null){
                throw new CustomException("用户不存在！");
            }
            if(!sysUser.getPassword().equals(Md5Util.md5Encode(pwd))){
                throw new CustomException("账号或密码错误！");
            }
            String userId = sysUser.getId();
            //权限菜单
            List<SysPermission> menus = sysPermissionDao.findMenuListByUserId(userId);
             //权限资源
             List<SysPermission>  permissions = sysPermissionDao.findPermissionListByUserId(userId);
             ActiveUser activeUser =new ActiveUser();
            activeUser.setUserId(sysUser.getId());
            activeUser.setUserCode(sysUser.getUserCode());
            activeUser.setUserName(sysUser.getUserName());
            activeUser.setMenus(menus);
            activeUser.setPermissions(permissions);
             return  activeUser;
    }



}
