package com.hh.dao;

import entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.jnlp.UnavailableServiceException;

/**
 * Created by hh on 15-10-21.
 */
@Repository
public class UserDao extends  BaseDao{

    public User getUserByCode(String code){
        return (User)this.selectOne("UserMapper.getUserByUserCode",code);
    }
}
