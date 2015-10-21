package com.hh.dao;

import entity.SysPermission;

import java.util.List;

/**
 * Created by hh on 15-10-21.
 */
public class SysPermissionDao extends BaseDao{

    /**
     * 获得权限菜单
     * @param userId
     * @return
     */
    public List<SysPermission> findMenuListByUserId(String userId){
        return this.selectList("SysPermissionMapper.findMenuListByUserId",userId);
    }

    /**
     * 获得权限url
     * @param userId
     * @return
     */
    public List<SysPermission> findPermissionListByUserId(String userId){
        return this.selectList("SysPermissionMapper.findPermissionListByUserId",userId);
    }
}
