package com.hh.service;

import com.hh.dao.ItemDao;
import com.hh.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 15-10-23.
 */
@Service
public class ItemsService {

    @Autowired
    private ItemDao itemDao;
    /**
     * 获得商品列表
     * @return
     */
    public List<Items> findtemsList(){
        return itemDao.findtemsList();
    }
}
