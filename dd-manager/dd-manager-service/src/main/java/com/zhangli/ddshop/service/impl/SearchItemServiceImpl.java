package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.dao.TbItemSearchCustomMapper;
import com.zhangli.ddshop.pojo.vo.TbItemSearchCustom;
import com.zhangli.ddshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomDao;
    @Autowired
    private SolrServer solrServer;
    @Override
    public boolean importAllItems() {
        //采集数据：查询数据到List
        List<TbItemSearchCustom> list=tbItemSearchCustomDao.listSearchItems();
        //创建索引库 documentList

        //遍历
        for (TbItemSearchCustom tbItemSearchCustom:list){
            //创建solr文档对象
            SolrInputDocument document=new SolrInputDocument();
            //向文档对象中添加域：对应schema.xml配置文件中的域名
            document.addField("id", tbItemSearchCustom.getId());
            document.addField("item_title", tbItemSearchCustom.getTitle());
            document.addField("item_sell_point", tbItemSearchCustom.getSellPoint());
            document.addField("item_price", tbItemSearchCustom.getPrice());
            document.addField("item_image", tbItemSearchCustom.getImage());
            document.addField("item_category_name", tbItemSearchCustom.getCatName());

            //
            try {
                solrServer.add(document);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //提交
        try {
            solrServer.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
