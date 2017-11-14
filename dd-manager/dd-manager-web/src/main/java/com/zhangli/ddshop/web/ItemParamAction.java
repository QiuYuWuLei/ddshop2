package com.zhangli.ddshop.web;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.pojo.po.TbItemParam;
import com.zhangli.ddshop.pojo.vo.TbItemParamCustom;
import com.zhangli.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/itemParams")
    public Result<TbItemParamCustom> listItemParamsByPage(Page page){
        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParamsByPage(page);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/item/param/save/{cid}")
    @ResponseBody
    public int saveItemParam(@PathVariable("cid") Long cid,TbItemParam tbItemParam){
        tbItemParam.setItemCatId(cid);
        return itemParamService.saveItemParam(tbItemParam);
    }

    @RequestMapping(value = "/itemParam/query/{cid}",method = RequestMethod.GET)
    @ResponseBody
    public TbItemParam getItemParamByCid(@PathVariable("cid") Long cid){
        TbItemParam itemParam=null;
        try {
            itemParam = itemParamService.getItemParamByCid(cid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
         return itemParam;
    }
}
