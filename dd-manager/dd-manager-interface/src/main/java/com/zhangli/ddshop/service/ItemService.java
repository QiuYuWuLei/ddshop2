package com.zhangli.ddshop.service;

import com.zhangli.ddshop.common.dto.Order;
import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.pojo.po.TbItem;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

public  interface  ItemService {
    TbItem findById (Long itemId);
    List<TbItem> listItems();
    //分页
    Result<TbItemCustom> listItemsByPage(Page page, Order order);
    //删除商品
    int updateBatch(List<Long> ids);

    //上架商品
    int updateBatchUp(List<Long> ids);

    //下架商品
    int updateBatchDown(List<Long> ids);
}
