package com.hh.service;

import com.hh.dao.UserDao;
import com.hh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 15-10-25.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getUserByCode(String code){
        return  userDao.getUserByCode(code);
    }
}
