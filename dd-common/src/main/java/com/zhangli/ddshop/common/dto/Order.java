package com.zhangli.ddshop.common.dto;

import java.util.ArrayList;
import java.util.List;

//easyui的排序实体类
public class Order {
    private String sort;
    private String order;
    private List<String> orderParams;

    public List<String> getOrderParams() {
        String[] sorts=this.sort.split(",");//id title
        String[] orders=this.order.split(",");//asc  desc
        ArrayList<String>list=new ArrayList<>();
        for (int i=0;i<sorts.length;i++){
            String temp=sorts[i]+" "+orders[i];//id asc;title desc
            list.add(temp); //[id asc;title desc]
        }
        return list;
    }

    public void setOrderParams(List<String> orderParams) {
        this.orderParams = orderParams;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
