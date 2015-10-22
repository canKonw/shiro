package com.hh.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/9/28 0028.
 */
public class ItemsCustomVo  implements Serializable{

    private List<ItemsCustom>  itemsList;

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }
}
