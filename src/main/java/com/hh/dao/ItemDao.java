package com.hh.dao;

import com.hh.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 15-10-23.
 */
@Repository
public class ItemDao extends  BaseDao{

    /**
     * 获得商品列表
     * @return
     */
    public List<Items> findtemsList(){
        return this.selectList("ItemsMapper.findtemsList",null);
    }
}
