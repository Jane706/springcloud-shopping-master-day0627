package com.ying.util;

import java.util.List;

/**
 * 分页工具类
 */
public class PageInfo<T> {

    private List<T> data; // 数据
    private int pageNo; // 页码
    private int pageSize; // 页大小
    private int totalRecords; // 总记录数

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNo() { // 当前页码
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    // 自定义
    // 总页数
    public int pages() {
        return totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
    }

    // 第一页
    public int firstPage() {
        return 1;
    }

    // 下一页
    public Integer nextPage() {
        return this.pageNo + 1;
    }

    // 上一页
    public Integer prePage() {
        return this.pageNo - 1;
    }

    // 最后页
    public Integer lastPage() {
        return this.pages();
    }

    //是否为第一页
    private boolean isFirstPage;
    //是否为最后一页
    private boolean isLastPage;
    //是否有前一页
    private boolean hasPreviousPage;
    //是否有下一页
    private boolean hasNextPage;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepageNums;
    //导航条上的第一页
    private int navigateFirstPage;
    //导航条上的最后一页
    private int navigateLastPage;

    public PageInfo() {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }

}
