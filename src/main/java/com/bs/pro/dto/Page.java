package com.bs.pro.dto;

public class Page {

    private int total; // 总行数
    private int page = 1;// 当前页
    private int allPage;// 总页数
    private int pagesize = 10;// 每页行数

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

}
