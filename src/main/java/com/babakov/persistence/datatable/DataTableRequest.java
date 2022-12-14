package com.babakov.persistence.datatable;

import java.util.HashMap;
import java.util.Map;

public class DataTableRequest {
    private int page;
    private int size;
    private String order;
    private String sort;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest findAllRequest() {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.page = 1;
        dataTableRequest.size = Integer.MAX_VALUE;
        dataTableRequest.order = "asc";
        dataTableRequest.sort = "id";
        return dataTableRequest;
    }

    public DataTableRequest() {
        this.requestParamMap = new HashMap<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, String[]> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, String[]> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }
}
