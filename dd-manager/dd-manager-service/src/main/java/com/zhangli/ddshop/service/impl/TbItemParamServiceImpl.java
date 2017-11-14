package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.common.dto.Page;
import com.zhangli.ddshop.common.dto.Result;
import com.zhangli.ddshop.dao.TbItemParamMapper;
import com.zhangli.ddshop.dao.TbItemParamMapperCustom;
import com.zhangli.ddshop.pojo.po.TbItemParam;
import com.zhangli.ddshop.pojo.po.TbItemParamExample;
import com.zhangli.ddshop.pojo.vo.TbItemParamCustom;
import com.zhangli.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TbItemParamServiceImpl implements ItemParamService{
    private  Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemParamMapperCustom itemParamCustomDao;

    @Autowired
    private TbItemParamMapper itemParamDao;
    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom>result=null;
        try {
            //DAO层接口多多个参数解决方案之一
            HashMap<String,Object> map=new HashMap<>();
            map.put("page",page);
            //取出tb_item_param这张表记录条数
            int total = itemParamCustomDao.countItemParams();
            result=new Result<>();
            result.setTotal(total);
            //取出对应页码的记录集合
            List<TbItemParamCustom> list = itemParamCustomDao.listItemParamsByPage(map);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int saveItemParam(TbItemParam tbItemParam) {
        int result=0;
        try {
            tbItemParam.setCreated(new Date());
            tbItemParam.setUpdated(new Date());
            result = itemParamDao.insert(tbItemParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam tbItemParam=null;
        try {
            TbItemParamExample example=new TbItemParamExample();
            TbItemParamExample.Criteria criteria=example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);

            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
            if(list !=null && list.size()>0){
                tbItemParam = list.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbItemParam;
    }
}
