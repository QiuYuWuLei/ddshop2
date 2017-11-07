package com.zhangli.ddshop.service;

import com.zhangli.ddshop.pojo.po.TbItem;

import java.util.List;

public  interface  ItemService {
    TbItem findById (Long itemId);
    List<TbItem> listItems();
}
