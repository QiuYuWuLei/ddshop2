package com.zhangli.ddshop.service;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.pojo.po.TbItem;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

public  interface  ItemService {
    TbItem findById (Long itemId);
    List<TbItem> listItems();
    Result<TbItemCustom> listItemsByPage(Page page);
}
