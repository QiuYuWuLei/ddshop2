package com.zhangli.ddshop.search.web;

import com.zhangli.ddshop.common.util.PropKit;
import com.zhangli.ddshop.pojo.po.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchIndexAction {
    @RequestMapping("/")
    public  String searchIndex(Model model){
        return "search";
    }
}
