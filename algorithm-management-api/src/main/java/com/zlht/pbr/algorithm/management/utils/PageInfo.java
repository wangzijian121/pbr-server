
package com.zlht.pbr.algorithm.management.utils;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

/**
 * page info
 *
 * @param <T> model
 */
public class PageInfo<T> {

    /**
     * totalList
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> totalList = Collections.emptyList();
    /**
     * total
     */
    @ApiModelProperty(value = "数据总数")
    private Integer total = 0;
    /**
     * total Page
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    /**
     * page size
     */
    @ApiModelProperty(value = "页大小")
    private Integer pageSize = 20;
    /**
     * current page
     */
    @ApiModelProperty(value = "当前页数")
    private Integer currentPage = 0;


    public PageInfo() {

    }

    public PageInfo(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }


    public List<T> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<T> totalList) {
        this.totalList = totalList;
    }

    public Integer getTotal() {
        if (total == null) {
            total = 0;
        }
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        //如果能整除，那么总页数就等于总数据量除以每页显示的数据量
        // 如果不能整除，那么总页数就等于总数据量除以每页显示的数据量再加1。
        this.totalPage =
                (this.total % this.pageSize) == 0
                        ? ((this.total / this.pageSize) == 0 ? 1 : (this.total / this.pageSize))
                        : (this.total / this.pageSize + 1);
        return this.totalPage;
    }


    public Integer getPageSize() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 7;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        if (currentPage == null || currentPage <= 0) {
            this.currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}