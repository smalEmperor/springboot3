package com.vue.boot.spring.study.domain;

import com.mybatisflex.core.paginate.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果封装工具类
 * @author
 * @date 2019/8/6
 */
@Data
public class PageResult implements Serializable {

    /**
     * 封装分页结果
     *
     * @param data 数据
     */
    public static Map<String, Object> requestSuccessPage(Page<?> data) {
        Map<String, Object> resultObj = new HashMap<>(8);
        resultObj.put("pageNum", data.getPageNumber());
        resultObj.put("pageSize", data.getPageSize());
        resultObj.put("total", data.getTotalRow());
        resultObj.put("pages", data.getTotalPage());
        resultObj.put("list", data.getRecords());
        return resultObj;
    }

    /**
     * 封装分页结果
     *
     * @param data 数据
     * @return
     */
    public static Map<String, Object> requestSuccessPage(Page<?> data, List<?> list) {
        Map<String, Object> resultObj = new HashMap<>(8);
        resultObj.put("pageNum", data.getPageNumber());
        resultObj.put("pageSize", data.getPageSize());
        resultObj.put("total", data.getTotalRow());
        resultObj.put("pages", data.getTotalPage());
        resultObj.put("records", list);
        return resultObj;
    }

}
