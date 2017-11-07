package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.dao.TbItemMapper;
import com.zhangli.ddshop.dao.TbItemMapperCustom;
import com.zhangli.ddshop.pojo.po.TbItem;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;
import com.zhangli.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemMapperCustom itemCustomDao;

    @Override
    public TbItem findById(Long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return  tbItem;
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> list=null;
        try {
         list= itemDao.selectByExample(null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  list;
    }


    //分页查询
    @Override
    public Result<TbItemCustom> listItemsByPage(Page page) {
       Result<TbItemCustom> result=new Result<>();
        try {
            int total = itemCustomDao.count();
            result.setTotal(total);
            List<TbItemCustom> rows = itemCustomDao.listItemsByPage(page);
            result.setRows(rows);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }
}