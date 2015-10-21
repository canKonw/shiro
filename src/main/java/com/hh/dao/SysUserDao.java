package com.hh.dao;

import entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by hh on 15-10-21.
 */
@Repository
public class SysUserDao  extends BaseDao{

    public SysUser getSysUserByCode(String code){
      return  (SysUser)this.selectOne("SysUserMapper",code);
    }
}
