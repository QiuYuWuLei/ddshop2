package com.zhangli.ddshop.dao;

import com.zhangli.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TbItemParamMapperCustom {
    int countItemParams();
    List<TbItemParamCustom>listItemParamsByPage(Map<String,Object> map);
}
