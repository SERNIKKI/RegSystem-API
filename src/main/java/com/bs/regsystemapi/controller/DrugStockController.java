package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.DrugStock;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.*;
import com.bs.regsystemapi.modal.vo.drug.*;
import com.bs.regsystemapi.service.DrugStockService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import com.bs.regsystemapi.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author qpj
 * @date 2022/3/22 15:28
 */
@RestController
@RequestMapping("/drugStock")
@Api(value = "药品库存接口")
public class DrugStockController {

    @Autowired
    private DrugStockService drugStockService;
    @Autowired
    private LogService logService;

    private static final String[] itemStyles = {"#546fc5", "#90cb74", "#fac758", "#ee6566", "#72bfdd", "#3aa171", "#fc8352", "#995fb4", "#e97bcb", "#409EFF"};

    @ApiOperation(value = "获取还没有库存记录的药品列表")
    @GetMapping(value = "/notStockList")
    public ResponseResult getNotStockList() {
        List<NotStockInfo> notStockList = drugStockService.getNotStockList();
        return ResponseResult.ok().put(notStockList);
    }

    @ApiOperation(value = "获取库存列表")
    @PostMapping(value = "/list")
    public ResponseResult getStockList(@RequestBody QueryStockForm form) {
        ManagePageResult stockList = drugStockService.getStockList(form);
        return ResponseResult.ok().put(stockList);
    }

    @ApiOperation(value = "获取药品库存信息")
    @GetMapping("/info/{drugNo}")
    public ResponseResult getStockInfo(@PathVariable("drugNo") String drugNo) {
        if(StringUtils.isEmpty(drugNo)) {
            return ResponseResult.error("药物no不能为空！");
        }
        StockInfo stockInfo = drugStockService.getStockInfo(drugNo);
        return ResponseResult.ok().put(stockInfo);
    }

    @ApiOperation(value = "获取指定月份的库存信息")
    @PostMapping("/info/month")
    public ResponseResult getStockInfoForMonth(@RequestBody QueryStockForMonthForm form) {
        if(StringUtils.isEmpty(form.getDrugNo())) {
            return ResponseResult.error("药物no不能为空！");
        }
        if(String.valueOf(form.getMonth()).equals("")) {
            form.setMonth(TimeUtils.getMonth());
        }
        if(form.getMonth() > 12 || form.getMonth() < 1) {
            return ResponseResult.error("月份不能超出范围！");
        }
        StockForMonthInfo stockInfoForMonth = drugStockService.getStockInfoForMonth(form);
        stockInfoForMonth.setMonth(form.getMonth());
        return ResponseResult.ok().put(stockInfoForMonth);
    }

    @ApiOperation(value = "获取库存相关报表")
    @PostMapping("/report")
    public ResponseResult getStockReport(@RequestBody QueryStockReportForm form) {
        // 如果没有传月份，默认当月
        if(form.getMonth() == 0) {
            form.setMonth(TimeUtils.getMonth());
        }
        // 判断月份格式是否正确
        if(form.getMonth() > 15 || form.getMonth() < 1) {
            return ResponseResult.error("月份不能超出范围！");
        }
        if(form.getDrugType() == null || form.getDrugType().equals("")) {
            form.setDrugType("1");
        }
        if(form.getLimit() < 5) {
            form.setLimit(5);
        }
        if(form.getSort() == null || form.getSort().equals("")) {
            form.setSort("desc");
        }
        List<StockReportInfo> stockReport = drugStockService.getStockReport(form);
        int i = 0;
        for(StockReportInfo stockReportInfo : stockReport) {
            stockReportInfo.setItemStyle(itemStyles[i]);
            i++;
            if(i > 10) {
                i = 0;
            }
        }
        return ResponseResult.ok().put(stockReport);
    }

    @ApiOperation(value = "批量新增库存")
    @PostMapping(value = "/addStock")
    public ResponseResult updateStockInfo(@RequestBody List<QueryAddStockForm> forms) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<QueryAddStockForm> error = new ArrayList<>();
        List<QueryAddStockForm> success = new ArrayList<>();
        List<String> successNos = new ArrayList<>();
        List<String> failNos = new ArrayList<>();
        for(QueryAddStockForm form : forms) {
            if(StringUtils.isEmpty(form.getDrugNo())) {
                error.add(form);
                continue;
            }
            StockInfo stockInfo = new StockInfo();
            stockInfo.setDrugNo(form.getDrugNo());
            stockInfo.setCountTotal((long) form.getCount());
            stockInfo.setUpdateName(user.getUserRealName());
            stockInfo.setUpdateTime(new Date());
            int i = drugStockService.updateStockInfo(stockInfo);
            if(i > 0) {
                success.add(form);
                successNos.add(form.getDrugNo());
            } else {
                error.add(form);
                failNos.add(form.getDrugNo());
            }
        }
        logService.addLog(Action.BATCH_UPDATE, Table.DRUG_STOCK, successNos.toString());
        if(failNos.size() > 0) {
            logService.addLog(Action.BATCH_UPDATE, Table.DRUG_STOCK, failNos.toString(), Status.FAIL);
        }
        UpdateStockResult updateStockResult = new UpdateStockResult();
        updateStockResult.setError(error);
        updateStockResult.setTotalError(error.size());
        updateStockResult.setSuccess(success);
        updateStockResult.setTotalSuccess(success.size());
        return ResponseResult.ok().put(updateStockResult);
    }

    @ApiOperation(value = "批量减少库存")
    @PostMapping(value = "/reduceStock")
    public ResponseResult updateStockInfo2(@RequestBody List<QueryAddStockForm> forms) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<QueryAddStockForm> error = new ArrayList<>();
        List<QueryAddStockForm> success = new ArrayList<>();
        List<String> successNos = new ArrayList<>();
        List<String> failNos = new ArrayList<>();
        for(QueryAddStockForm form : forms) {
            if(StringUtils.isEmpty(form.getDrugNo())) {
                error.add(form);
                continue;
            }
            StockInfo stockInfo = new StockInfo();
            stockInfo.setDrugNo(form.getDrugNo());
            stockInfo.setCountTotal((long) form.getCount());
            stockInfo.setUpdateName(user.getUserRealName());
            stockInfo.setUpdateTime(new Date());
            int i = drugStockService.updateStockInfo2(stockInfo);
            if(i > 0) {
                success.add(form);
                successNos.add(form.getDrugNo());
            } else {
                error.add(form);
                failNos.add(form.getDrugNo());
            }
        }
        logService.addLog(Action.BATCH_UPDATE, Table.DRUG_STOCK, successNos.toString());
        if(failNos.size() > 0) {
            logService.addLog(Action.BATCH_UPDATE, Table.DRUG_STOCK, failNos.toString(), Status.FAIL);
        }
        UpdateStockResult updateStockResult = new UpdateStockResult();
        updateStockResult.setError(error);
        updateStockResult.setTotalError(error.size());
        updateStockResult.setSuccess(success);
        updateStockResult.setTotalSuccess(success.size());
        return ResponseResult.ok().put(updateStockResult);
    }

    @ApiOperation(value = "新增库存记录")
    @PostMapping(value = "/addInfo")
    public ResponseResult addStockInfo(@RequestBody DrugStock drugStock) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugStock.setUpdateName(user.getUserRealName());
        drugStock.setUpdateTime(new Date());
        boolean save = drugStockService.save(drugStock);
        logService.addLog(Action.INSERT, Table.DRUG_STOCK, drugStock.getDrugNo(),save ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "一键生成库存记录")
    @GetMapping("/saveByGenerate")
    public ResponseResult saveByGenerate() {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<NotStockInfo> notStockList = drugStockService.getNotStockList();
        List<DrugStock> stocks = new ArrayList<>();
        List<String> nos = new ArrayList<>();
        for(NotStockInfo notStockInfo: notStockList) {
            DrugStock drugStock = new DrugStock();
            drugStock.setDrugNo(notStockInfo.getDrugNo());
            drugStock.setDrugType(notStockInfo.getDrugType());
            drugStock.setUpdateTime(new Date());
            drugStock.setUpdateName(user.getUserRealName());
            nos.add(drugStock.getDrugNo());
            stocks.add(drugStock);
        }
        boolean b = drugStockService.saveBatch(stocks);
        logService.addLog(Action.BATCH_INSERT, Table.DRUG_STOCK, nos.toString(),b ? Status.SUCCESS : Status.FAIL);
        if(b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("生成失败！");
        }
    }

    @ApiOperation(value = "批量新增库存记录")
    @PostMapping(value = "/addList")
    public ResponseResult addStockList(@RequestBody List<DrugStock> drugStockList) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<String> nos = new ArrayList<>();
        for(DrugStock drugStock : drugStockList) {
            drugStock.setUpdateName(user.getUserRealName());
            drugStock.setUpdateTime(new Date());
            nos.add(drugStock.getDrugNo());
        }
        boolean b = drugStockService.saveBatch(drugStockList);
        logService.addLog(Action.BATCH_INSERT, Table.DRUG_STOCK, nos.toString(),b ? Status.SUCCESS : Status.FAIL);
        if(b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("新增失败！");
        }
    }

    @ApiOperation(value = "获取全年每个月总售出量")
    @GetMapping("/report/allMonth")
    public ResponseResult getAllMonthReport() {
        CountForMonthInfo allMonthReport = drugStockService.getAllMonthReport();
        List<Integer> list = new ArrayList<>();
        list.add(allMonthReport.getJan());
        list.add(allMonthReport.getFeb());
        list.add(allMonthReport.getMar());
        list.add(allMonthReport.getApr());
        list.add(allMonthReport.getMay());
        list.add(allMonthReport.getJun());
        list.add(allMonthReport.getJul());
        list.add(allMonthReport.getAug());
        list.add(allMonthReport.getSep());
        list.add(allMonthReport.getOct());
        list.add(allMonthReport.getNov());
        list.add(allMonthReport.getDec());
        return ResponseResult.ok().put(list);
    }

    @ApiOperation(value = "获取药品每个月售出量")
    @PostMapping("/report/everyMonth")
    public ResponseResult getEveryMonthReport(@RequestBody QueryEveryMonthReportForm form) {
        if(form.getDrugType() == null || form.getDrugType().equals("")) {
            form.setDrugType("1");
        }
        if(form.getLimit() < 5) {
            form.setLimit(5);
        }
        if(form.getSort() == null || form.getSort().equals("")) {
            form.setSort("desc");
        }
        List<StockMonthInfo> everyMonthReport = drugStockService.getEveryMonthReport(form);
        return ResponseResult.ok().put(everyMonthReport);
    }
}
