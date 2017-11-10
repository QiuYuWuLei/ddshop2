package com.zhangli.ddshop.service;

import com.zhangli.ddshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}
