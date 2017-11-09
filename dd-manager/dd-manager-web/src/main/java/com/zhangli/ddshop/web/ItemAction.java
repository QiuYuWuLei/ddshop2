package com.zhangli.ddshop.web;

import com.zhangli.ddshop.common.dto.Order;
import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.pojo.po.TbItem;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;
import com.zhangli.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Scope("prototype")
public class ItemAction {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public TbItem getById(@PathVariable("itemId") Long itemId){
        return itemService.findById(itemId);
    }

   /* @RequestMapping(value = "/items")
    @ResponseBody
    public List<TbItem> listItems(){
        List<TbItem> list=null;
        try {
            list=itemService.listItems();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  list;
    }*/

   //分页查询
   @RequestMapping(value = "/items")
   @ResponseBody
    public Result<TbItemCustom>listItemsByPage(Page page,Order order){
       Result<TbItemCustom> result=null;
       try {
           result=itemService.listItemsByPage(page,order);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
       return  result;
   }

   //删除商品
    @RequestMapping("/items/batch")
    @ResponseBody
    public int  updateBatch(@RequestParam("ids[]") List<Long> ids){
        int result=0;
        try {
            result=itemService.updateBatch(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }

    //上架商品
    @RequestMapping("/items/batchUp")
    @ResponseBody
    public  int  updateBatchUp(@RequestParam("ids[]") List<Long> ids){
        int result=0;
        try {
            result = itemService.updateBatchUp(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }


    //下架商品
    @RequestMapping("/items/batchDown")
    @ResponseBody
    public  int  updateBatchDown(@RequestParam("ids[]") List<Long> ids){
        int result=0;
        try {
            result = itemService.updateBatchDown(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }
}
