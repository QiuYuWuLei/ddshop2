package com.zhangli.ddshop.service.impl;

import com.mysql.jdbc.StringUtils;
import com.zhangli.ddshop.common.jedis.JedisClient;
import com.zhangli.ddshop.common.util.JsonUtils;
import com.zhangli.ddshop.dao.TbContentMapper;
import com.zhangli.ddshop.pojo.po.TbContent;
import com.zhangli.ddshop.pojo.po.TbContentExample;
import com.zhangli.ddshop.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentServiceImpl implements ContentService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper tbContentDao;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public List<TbContent> listContentsByCid(Long id) {
        List<TbContent>list=null;
        try {
            //查询缓存部分
            String json = jedisClient.hget("CONTENT_LIST", id + "");
            if(StringUtils.isNullOrEmpty(json)){
                return JsonUtils.jsonToList(json,TbContent.class);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        //主体业务部分
        //创建模板
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        //执行查询
        list=tbContentDao.selectByExample(example);

        //存入缓存部分
        try {
            jedisClient.hset("CONTENT_LIST",id+"", JsonUtils.objectToJson(list));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }
}
