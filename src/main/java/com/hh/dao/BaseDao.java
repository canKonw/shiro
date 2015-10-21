package com.hh.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hh on 15-10-21.
 */
@Component
public class BaseDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 获得单个数据
     * @param statement
     * @param params
     * @return
     */
    public Object selectOne(String statement,Object params){
        return  sqlSessionTemplate.selectOne(statement,params);
    }

    public List selectList(String statement,Object params){
        return sqlSessionTemplate.selectList(statement,params);
    }
}
