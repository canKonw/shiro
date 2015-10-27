package com.hh.controller;


import com.hh.entity.Items;
import com.hh.service.ItemsService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2015/9/17 0017.
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    //注入service
    @Autowired
    private ItemsService itemsService;


    @ModelAttribute("itemtype")
    public Map<Object,Object> getItemtype(){
        Map<Object,Object> map=new HashMap<Object,Object>();
        map.put("1","商品类型1");
        map.put("2","商品类型2");
        return map;
    }

    /**
     * 商品展示
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryItems")
    @RequiresPermissions("item:query")//表示必须拥有“item:query”权限方可执行
    public ModelAndView queryItems(javax.servlet.http.HttpServletRequest httpServletRequest,
                                   javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        //如果是转发：httpServletRequest的数据是可以共享的

        //商品列表
        List<Items> itemsList = itemsService.findtemsList();
        //创建modelAndView准备填充数据、设置视图
        ModelAndView modelAndView = new ModelAndView();
        //填充数据
        modelAndView.addObject("itemsList", itemsList);
        //视图
        modelAndView.setViewName("order/itemsList");

        return modelAndView;
    }


}
