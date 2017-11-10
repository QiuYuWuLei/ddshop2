package com.zhangli.ddshop.dao;

import com.zhangli.ddshop.common.dto.Order;
import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;
import com.zhangli.ddshop.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* 自定义Item  Dao层查询
*/
public interface TbItemMapperCustom {
    //查询所有记录数的总数
    int count(@Param("query")TbItemQuery query);

    List<TbItemCustom> listItemsByPage(@Param("page") Page page, @Param("order") Order order, @Param("query")TbItemQuery query);
}
