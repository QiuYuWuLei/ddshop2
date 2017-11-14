package com.zhangli.ddshop.service;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.pojo.po.TbItemParam;
import com.zhangli.ddshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {
    /**
     * 对参数规格说明表进行分页操作
     * @param page
     * @return
     */
    Result<TbItemParamCustom> listItemParamsByPage(Page page);

    int saveItemParam(TbItemParam tbItemParam);

    TbItemParam getItemParamByCid(Long cid);
}
