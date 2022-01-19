package ua.com.alevel.persistence.datatable;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public class DataTableRequest<ENTITY extends BaseEntity> {

    private String sort;
    private String order;
    private int currentPage;
    private int pageSize;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest() {
        this.requestParamMap = new HashMap<>();
    }

    public Map<String, String[]> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, String[]> requestParamMap) {
        this.requestParamMap = requestParamMap;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
