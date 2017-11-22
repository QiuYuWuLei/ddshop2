package com.zhangli.ddshop.service;

import com.zhangli.ddshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {

    //根据内容分类的编号查询出内容
    List<TbContent> listContentsByCid(Long id);
}
