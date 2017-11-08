package com.zhangli.ddshop.pojo.vo;

import com.zhangli.ddshop.pojo.po.TbItem;

public class TbItemCustom  extends TbItem{
    private  String catName;
    private String  statusName;
    private  String priceView;

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
