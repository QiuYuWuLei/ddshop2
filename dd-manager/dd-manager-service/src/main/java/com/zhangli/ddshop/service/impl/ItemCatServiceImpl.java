package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.common.dto.TreeNode;
import com.zhangli.ddshop.dao.TbItemCatMapper;
import com.zhangli.ddshop.pojo.po.TbItemCat;
import com.zhangli.ddshop.pojo.po.TbItemCatExample;
import com.zhangli.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl  implements ItemCatService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatDao;
    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodeList = null;
        try {
            TbItemCatExample example=new TbItemCatExample();
            TbItemCatExample.Criteria criteria=example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            treeNodeList=new ArrayList<>();
            List<TbItemCat> itemCatList = itemCatDao.selectByExample(example);
            for (int i=0;i<itemCatList.size();i++){
                TbItemCat tbItemCat = itemCatList.get(i);
                TreeNode treeNode=new TreeNode();
                treeNode.setId(tbItemCat.getId());
                treeNode.setText(tbItemCat.getName());
                treeNode.setState(tbItemCat.getIsParent() ? "closed":"open");

                treeNodeList.add(treeNode);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return  treeNodeList;
    }
}
