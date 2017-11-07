package com.zhangli.ddshop.dao;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

/*
* 自定义Item  Dao层查询
*/
public interface TbItemMapperCustom {
    //查询所有记录数的总数
    int count();

    List<TbItemCustom> listItemsByPage(Page page);
}
