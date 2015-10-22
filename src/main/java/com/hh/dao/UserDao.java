package com.hh.dao;

import com.hh.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by hh on 15-10-21.
 */
@Repository
public class UserDao extends  BaseDao{

    public User getUserByCode(String code){
        return (User)this.selectOne("UserMapper.getUserByUserCode",code);
    }
}
