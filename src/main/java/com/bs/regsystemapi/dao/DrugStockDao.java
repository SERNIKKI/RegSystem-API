package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugStock;
import com.bs.regsystemapi.modal.dto.drug.QueryEveryMonthReportForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForMonthForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockReportForm;
import com.bs.regsystemapi.modal.vo.drug.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/22 15:09
 */
@Mapper
public interface DrugStockDao extends BaseMapper<DrugStock> {
    List<NotStockInfo> getNotStockList();
    List<StockInfo> getStockList(QueryStockForm form);
    StockInfo getStockInfo(String drugNo);
    StockForMonthInfo getStockInfoForMonth(QueryStockForMonthForm form);
    int updateStockInfo(StockInfo stockInfo);
    int updateStockInfo2(StockInfo stockInfo);
    List<StockReportInfo> getStockReport(QueryStockReportForm form);
    CountForMonthInfo getAllMonthReport();
    List<StockInfo> getEveryMonthReport(QueryEveryMonthReportForm form);
}
