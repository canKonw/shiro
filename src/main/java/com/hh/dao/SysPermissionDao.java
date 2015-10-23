package com.hh.dao;

import com.hh.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hh on 15-10-21.
 */
@Repository
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
