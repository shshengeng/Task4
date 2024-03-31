package com.example.common.utils;

import java.util.List;

public class PaginationUtil<T> {

    public List<T> paginate(List<T> dataList, int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, dataList.size());
        return dataList.subList(startIndex, endIndex);
    }
}