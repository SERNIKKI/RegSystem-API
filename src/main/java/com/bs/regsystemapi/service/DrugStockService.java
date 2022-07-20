package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugStock;
import com.bs.regsystemapi.modal.dto.drug.QueryEveryMonthReportForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForMonthForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockReportForm;
import com.bs.regsystemapi.modal.vo.drug.*;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/22 15:23
 */
public interface DrugStockService extends IService<DrugStock> {
    List<NotStockInfo> getNotStockList();
    ManagePageResult getStockList(QueryStockForm form);
    StockInfo getStockInfo(String drugNo);
    StockForMonthInfo getStockInfoForMonth(QueryStockForMonthForm form);
    int updateStockInfo(StockInfo stockInfo);
    int updateStockInfo2(StockInfo stockInfo);
    List<StockReportInfo> getStockReport(QueryStockReportForm form);
    CountForMonthInfo getAllMonthReport();
    List<StockMonthInfo> getEveryMonthReport(QueryEveryMonthReportForm form);
    void reduceDrugStock(long num, String drugNo);
}
