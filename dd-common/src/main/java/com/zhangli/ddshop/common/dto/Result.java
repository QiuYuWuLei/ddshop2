package com.zhangli.ddshop.common.dto;

import java.util.List;

public class Result<T> {
    //符合条件的记录总数
    private  int  total;
    //符合条件的记录集合
    private List<T>rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
