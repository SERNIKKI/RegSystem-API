package com.bs.regsystemapi.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ManagePageResult implements Serializable {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List content;
    /**
     * 数据扩展
     */
    private Map<String,Object> extMap;

    /**
     * 将分页信息封装到统一的接口
     * @param pageInfo
     * @return
     */
    public static ManagePageResult getPageResult(PageInfo pageInfo) {
        ManagePageResult managePageResult = new ManagePageResult();
        managePageResult.setPageNum(pageInfo.getPageNum());
        managePageResult.setPageSize(pageInfo.getPageSize());
        managePageResult.setTotalSize(pageInfo.getTotal());
        managePageResult.setTotalPages(pageInfo.getPages());
        managePageResult.setContent(pageInfo.getList());
        return managePageResult;
    }

    /**
     * 将分页信息封装到统一的接口
     * @param page
     * @param t
     * @return
     */
    public static <T extends ManagePageRequest> ManagePageResult getPageResult(IPage page, T t) {
        ManagePageResult managePageResult = new ManagePageResult();
        managePageResult.setPageNum(t.getPageNum());
        managePageResult.setPageSize(t.getPageSize());
        managePageResult.setTotalSize(page.getTotal());
        managePageResult.setTotalPages(Integer.parseInt(String.valueOf(page.getPages())));
        managePageResult.setContent(page.getRecords());
        return managePageResult;
    }
}
