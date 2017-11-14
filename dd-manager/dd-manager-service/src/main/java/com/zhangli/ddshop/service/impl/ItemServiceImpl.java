package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.common.dto.Order;
import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.common.util.IDUtils;
import com.zhangli.ddshop.dao.TbItemDescMapper;
import com.zhangli.ddshop.dao.TbItemMapper;
import com.zhangli.ddshop.dao.TbItemMapperCustom;
import com.zhangli.ddshop.pojo.po.TbItem;
import com.zhangli.ddshop.pojo.po.TbItemDesc;
import com.zhangli.ddshop.pojo.po.TbItemExample;
import com.zhangli.ddshop.pojo.vo.TbItemCustom;
import com.zhangli.ddshop.pojo.vo.TbItemQuery;
import com.zhangli.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemMapperCustom itemCustomDao;
    @Autowired
    private TbItemDescMapper itemDescDao;

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
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
       Result<TbItemCustom> result=new Result<>();
        try {
            int total = itemCustomDao.count(query);
            result.setTotal(total);
            List<TbItemCustom> rows = itemCustomDao.listItemsByPage(page,order,query);
            result.setRows(rows);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }

    //删除商品
    @Override
    public int updateBatch(List<Long> ids) {
        int result=0;
        try {
            //准备商品对象，这个对象包含了状态为3的字段
            TbItem record=new TbItem();
            record.setStatus((byte)3);

            //创建更新模板
            TbItemExample example=new TbItemExample();
            TbItemExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(ids);
            //调用dao层的方法    执行更新
            result = itemDao.updateByExampleSelective(record, example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }

    //上架商品
    @Override
    public int updateBatchUp(List<Long> ids) {
        int result=0;
        try {
            //准备商品对象，这个对象包含了状态为1的字段
            TbItem record=new TbItem();
            record.setStatus((byte)1);

            //创建更新模板
            TbItemExample example=new TbItemExample();
            TbItemExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(ids);

            //调用dao层的方法    执行更新
            result = itemDao.updateByExampleSelective(record, example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
       return  result;
    }

    //加下商品
    @Override
    public int updateBatchDown(List<Long> ids) {
        int result = 0;
        try {
            //准备商品对象，这个对象包含了状态为1的字段
            TbItem record = new TbItem();
            record.setStatus((byte) 2);

            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);

            //调用dao层的方法    执行更新
            result = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String desc) {
        Long id= IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        int count = itemDao.insert(tbItem);

        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        count+=itemDescDao.insert(itemDesc);
        return count;
    }
}
