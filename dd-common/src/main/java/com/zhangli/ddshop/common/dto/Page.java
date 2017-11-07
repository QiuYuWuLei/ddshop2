package com.zhangli.ddshop.common.dto;

public class Page {
    //当前 页的页码
    private  int page;
    //每页显示的条数
    private  int rows;
    //显示每页的开头的索引
    //  private  int  offset;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        return (page-1)*rows;
    }

   /* public void setOffset(int offset) {
        this.offset = offset;
    }*/

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
