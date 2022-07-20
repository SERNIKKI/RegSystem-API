package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugStockDao;
import com.bs.regsystemapi.entity.DrugStock;
import com.bs.regsystemapi.modal.dto.drug.QueryEveryMonthReportForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForMonthForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockForm;
import com.bs.regsystemapi.modal.dto.drug.QueryStockReportForm;
import com.bs.regsystemapi.modal.vo.drug.*;
import com.bs.regsystemapi.service.DrugStockService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/22 15:24
 */
@Service("drugStockService")
public class DrugStockServiceImpl extends ServiceImpl<DrugStockDao, DrugStock> implements DrugStockService {

    private static final String[] itemStyles = {"#546fc5", "#90cb74", "#fac758", "#ee6566", "#72bfdd", "#3aa171", "#fc8352", "#995fb4", "#e97bcb", "#409EFF"};

    @Autowired
    private DrugStockService drugStockService;

    @Override
    public List<NotStockInfo> getNotStockList() {
        List<NotStockInfo> notStockList = this.baseMapper.getNotStockList();
        return notStockList;
    }

    @Override
    public ManagePageResult getStockList(QueryStockForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<StockInfo> stockList = this.baseMapper.getStockList(form);
        PageInfo<StockInfo> stockInfoPageInfo = new PageInfo<>(stockList);
        return ManagePageResult.getPageResult(stockInfoPageInfo);
    }

    @Override
    public StockInfo getStockInfo(String drugNo) {
        StockInfo stockInfo = this.baseMapper.getStockInfo(drugNo);
        return stockInfo;
    }

    @Override
    public StockForMonthInfo getStockInfoForMonth(QueryStockForMonthForm form) {
        StockForMonthInfo stockInfoForMonth = this.baseMapper.getStockInfoForMonth(form);
        return stockInfoForMonth;
    }

    @Override
    public int updateStockInfo(StockInfo stockInfo) {
        int i = this.baseMapper.updateStockInfo(stockInfo);
        return i;
    }

    @Override
    public int updateStockInfo2(StockInfo stockInfo) {
        int i = this.baseMapper.updateStockInfo2(stockInfo);
        return i;
    }

    @Override
    public List<StockReportInfo> getStockReport(QueryStockReportForm form) {
        List<StockReportInfo> stockReport = this.baseMapper.getStockReport(form);
        return stockReport;
    }

    @Override
    public CountForMonthInfo getAllMonthReport() {
        CountForMonthInfo allMonthReport = this.baseMapper.getAllMonthReport();
        return allMonthReport;
    }

    @Override
    public List<StockMonthInfo> getEveryMonthReport(QueryEveryMonthReportForm form) {
        List<StockInfo> everyMonthReport = this.baseMapper.getEveryMonthReport(form);
        List<StockMonthInfo> stockMonthInfos = new ArrayList<>();
        int i = 0;
        for(StockInfo stockInfo : everyMonthReport) {
            StockMonthInfo info = new StockMonthInfo();
            List<Long> list = new ArrayList<>();
            list.add(stockInfo.getCountJan());
            list.add(stockInfo.getCountFeb());
            list.add(stockInfo.getCountMar());
            list.add(stockInfo.getCountApr());
            list.add(stockInfo.getCountMay());
            list.add(stockInfo.getCountJun());
            list.add(stockInfo.getCountJul());
            list.add(stockInfo.getCountAug());
            list.add(stockInfo.getCountSep());
            list.add(stockInfo.getCountOct());
            list.add(stockInfo.getCountNov());
            list.add(stockInfo.getCountDec());
            info.setData(list);
            info.setName(stockInfo.getDrugName());
            info.setItemStyle(itemStyles[i]);
            stockMonthInfos.add(info);
            i++;
            if(i > 10) {
                i = 0;
            }
        }
        return stockMonthInfos;
    }

    @Override
    @Transactional
    public void reduceDrugStock(long num, String drugNo) {
        // 获取当前月份
        int month = TimeUtils.getMonth();
        QueryWrapper<DrugStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drug_no", drugNo);
        DrugStock drugStock = this.baseMapper.selectOne(queryWrapper);
        UpdateWrapper<DrugStock> updateWrapper = new UpdateWrapper<>();
        String str = "";
        Long count = 0L;
        if(month == 1) {
            str = "count_Jan";
            count = drugStock.getCountJan();
        } else if(month == 2) {
            str = "count_Feb";
            count = drugStock.getCountFeb();
        } else if(month == 3) {
            str = "count_Mar";
            count = drugStock.getCountMar();
        } else if(month == 4) {
            str = "count_Apr";
            count = drugStock.getCountApr();
        } else if(month == 5) {
            str = "count_May";
            count = drugStock.getCountMay();
        } else if(month == 6) {
            str = "count_Jun";
            count = drugStock.getCountJun();
        } else if(month == 7) {
            str = "count_Jul";
            count = drugStock.getCountJul();
        } else if(month == 8) {
            str = "count_Aug";
            count = drugStock.getCountAug();
        } else if(month == 9) {
            str = "count_Sep";
            count = drugStock.getCountSep();
        } else if(month == 10) {
            str = "count_Oct";
            count = drugStock.getCountOct();
        } else if(month == 11) {
            str = "count_Nov";
            count = drugStock.getCountNov();
        } else if(month == 12) {
            str = "count_Dec";
            count = drugStock.getCountDec();
        }
        updateWrapper.eq("drug_no", drugNo)
                .set(str, count + num);
        drugStockService.update(updateWrapper);
    }
}
