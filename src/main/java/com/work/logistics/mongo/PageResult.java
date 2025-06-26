package com.work.logistics.mongo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private int pageNumber;          // 当前页码
    private int pageSize;            // 每页大小
    private int numberOfElements;    // 当前页元素数
    private boolean empty;           // 当前页是否为空
    private List<T> content;         // 当前页数据列表

    public PageResult(int pageNumber, int pageSize, int numberOfElements, boolean empty, List<T> content) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
        this.content = content;
    }

}
