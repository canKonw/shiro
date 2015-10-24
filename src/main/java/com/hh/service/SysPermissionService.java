package com.hh.service;

import com.hh.dao.SysPermissionDao;
import com.hh.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 15-10-25.
 */
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionDao sysPermissionDao;
    /**
     * 获得权限菜单
     * @param userId
     * @return
     */
    public List<SysPermission> findMenuListByUserId(String userId){
        return sysPermissionDao.findMenuListByUserId(userId);
    }

    /**
     * 获得权限url
     * @param userId
     * @return
     */
    public List<SysPermission> findPermissionListByUserId(String userId){
        return sysPermissionDao.findPermissionListByUserId(userId);
    }
}
