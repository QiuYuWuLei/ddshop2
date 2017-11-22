package com.zhangli.ddshop.web;

import com.zhangli.ddshop.common.dto.MessageResult;
import com.zhangli.ddshop.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SearchItemAction {
    @Autowired
    private SearchItemService searchItemService;
    @RequestMapping(value = "/search/item/import")
    @ResponseBody
    public MessageResult searchItemIndex(){
        //通过调用service层方法将采集到的数据
        boolean bool=searchItemService.importAllItems();
        MessageResult ms=new MessageResult();
        if(bool){
            ms.setSuccess(true);
            ms.setMessage("索引导入成功");
        }else {
            ms.setSuccess(false);
            ms.setMessage("索引导入失败");
        }

        return  ms;
    }
}
