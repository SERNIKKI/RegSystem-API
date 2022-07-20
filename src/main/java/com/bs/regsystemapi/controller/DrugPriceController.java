package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.DrugPrice;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.QueryPriceForm;
import com.bs.regsystemapi.modal.vo.drug.NotPriceInfo;
import com.bs.regsystemapi.modal.vo.drug.PriceInfo;
import com.bs.regsystemapi.service.DrugPriceService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/21 17:07
 */
@RestController
@RequestMapping("/drugPrice")
@Api(value = "药品价格接口")
public class DrugPriceController {

    private static final Logger logger = LoggerFactory.getLogger(DrugPriceController.class);

    @Autowired
    private DrugPriceService drugPriceService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "还没有生成价格记录的药品")
    @GetMapping("/notPriceList")
    public ResponseResult getNotPriceList() {
        List<NotPriceInfo> notPriceList = drugPriceService.getNotPriceList();
        return ResponseResult.ok().put(notPriceList);
    }

    @ApiOperation(value = "获取价格列表")
    @PostMapping(value = "/list")
    public ResponseResult getPriceList(@RequestBody QueryPriceForm form) {
        ManagePageResult priceList = drugPriceService.getPriceList(form);
        return ResponseResult.ok().put(priceList);
    }

    @ApiOperation(value = "获取价格详情")
    @GetMapping("/info/{drugNo}")
    public ResponseResult getPriceInfo(@PathVariable("drugNo") String drugNo) {
        PriceInfo priceInfo = drugPriceService.getPriceInfo(drugNo);
        return ResponseResult.ok().put(priceInfo);
    }

    @ApiOperation(value = "更新单个药品价格信息")
    @PostMapping(value = "/update")
    public ResponseResult updatePriceInfo(@RequestBody DrugPrice drugPrice) {
        if(StringUtils.isEmpty(drugPrice.getDrugNo())) {
            return ResponseResult.error("药品NO为空！");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugPrice.setUpdateName(user.getUserRealName());
        drugPrice.setUpdateTime(new Date());
        boolean b = drugPriceService.updateById(drugPrice);
        logService.addLog(Action.UPDATE, Table.DRUG_PRICE, drugPrice.getDrugNo(),b ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "批量更新价格")
    @PostMapping(value = "/updateByList")
    public ResponseResult updatePriceByList(@RequestBody List<DrugPrice> priceList) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<String> nos = new ArrayList<>();
        for(DrugPrice drugPrice : priceList) {
            drugPrice.setUpdateName(user.getUserRealName());
            drugPrice.setUpdateTime(new Date());
            nos.add(drugPrice.getDrugNo());
        }
        boolean b = drugPriceService.updateBatchById(priceList);
        logService.addLog(Action.BATCH_UPDATE, Table.DRUG_PRICE, nos.toString(),b ? Status.SUCCESS : Status.FAIL);
        if(b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("更新失败！");
        }
    }

    @ApiOperation(value = "新增价格记录")
    @PostMapping(value = "/save")
    public ResponseResult savePriceInfo(@RequestBody DrugPrice price) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        price.setCreateName(user.getUserRealName());
        price.setCreateTime(new Date());
        boolean save = drugPriceService.save(price);
        logService.addLog(Action.INSERT, Table.DRUG_PRICE, price.getDrugNo(),save ? Status.SUCCESS : Status.FAIL);
        if(save) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("新增失败！");
        }
    }

    @ApiOperation(value = "批量新增")
    @PostMapping(value = "/saveByList")
    public ResponseResult savePriceByList(@RequestBody List<DrugPrice> priceList) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<String> nos = new ArrayList<>();
        for(DrugPrice drugPrice : priceList) {
            drugPrice.setCreateName(user.getUserRealName());
            drugPrice.setCreateTime(new Date());
            nos.add(drugPrice.getDrugNo());
        }
        boolean b = drugPriceService.saveBatch(priceList);
        logService.addLog(Action.BATCH_INSERT, Table.DRUG_PRICE, nos.toString(),b ? Status.SUCCESS : Status.FAIL);
        if(b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("新增失败！");
        }
    }

    @ApiOperation(value = "一键生成价格记录")
    @GetMapping(value = "/saveByGenerate")
    public ResponseResult savePriceByList2() {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        List<NotPriceInfo> notPriceList = drugPriceService.getNotPriceList();
        if(notPriceList.size() == 0) {
            return ResponseResult.ok().put(0);
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<DrugPrice> prices = new ArrayList<>();
        List<String> nos = new ArrayList<>();
        for(NotPriceInfo notPriceInfo : notPriceList) {
            DrugPrice drugPrice = new DrugPrice();
            drugPrice.setDrugNo(notPriceInfo.getDrugNo());
            drugPrice.setDrugType(notPriceInfo.getDrugType());
            drugPrice.setCreateName(user.getUserRealName());
            drugPrice.setCreateTime(new Date());
            prices.add(drugPrice);
            nos.add(drugPrice.getDrugNo());
        }
        boolean b = drugPriceService.saveBatch(prices);
        logService.addLog(Action.BATCH_INSERT, Table.DRUG_PRICE, nos.toString(),b ? Status.SUCCESS : Status.FAIL);
        if(b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("生成失败！");
        }
    }

    @ApiOperation(value = "删除价格信息")
    @PostMapping("/delete")
    public ResponseResult deletePriceInfo(@RequestBody DrugPrice drugPrice) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行药品价格记录删除操作，药品no为{}",user.getUserName(),drugPrice.getDrugNo());
        drugPriceService.removeById(drugPrice.getId());
        logService.addLog(Action.DELETE, Table.DRUG_PRICE, drugPrice.getDrugNo());
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据no列表获取价格信息")
    @PostMapping(value = "/listByNos")
    public ResponseResult getListByNos(@RequestParam("noList") List<String> noList) {
        List<PriceInfo> priceInfos = new ArrayList<>();
        for(String no: noList) {
            PriceInfo priceInfo = drugPriceService.getPriceInfo(no);
            priceInfos.add(priceInfo);
        }
        return ResponseResult.ok().put(priceInfos);
    }
}
