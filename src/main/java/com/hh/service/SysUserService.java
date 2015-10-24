package com.hh.service;

import com.hh.dao.SysUserDao;
import com.hh.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 15-10-25.
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    public SysUser getSysUserByCode(String code){
        return sysUserDao.getSysUserByCode(code);
    }
}
