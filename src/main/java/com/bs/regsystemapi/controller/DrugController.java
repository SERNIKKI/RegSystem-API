package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Drug;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugForm;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugListForm;
import com.bs.regsystemapi.modal.vo.drug.DrugInfo;
import com.bs.regsystemapi.service.DrugService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 15:49
 */
@RestController
@RequestMapping("/drug")
@Api(value = "药品接口")
public class DrugController {

    private static final Logger logger = LoggerFactory.getLogger(DrugController.class);

    @Autowired
    private DrugService drugService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取药品列表")
    @PostMapping(value = "/list")
    public ResponseResult getDrugList(@RequestBody QueryDrugForm form) {
        ManagePageResult drugList = drugService.getDrugList(form);
        return ResponseResult.ok().put(drugList);
    }

    @ApiOperation(value = "获取药品列表")
    @PostMapping(value = "/v2/list")
    public ResponseResult getDrugList(@RequestBody QueryDrugListForm form) {
        ManagePageResult drugList = drugService.getDrugList(form);
        return ResponseResult.ok().put(drugList);
    }

    @ApiOperation(value = "获取药品详情")
    @PostMapping(value = "/info/{drugNo}")
    public ResponseResult getDrugInfo(@PathVariable("drugNo") String drugNo) {
        if(StringUtils.isEmpty(drugNo)) {
            return ResponseResult.error("药品no为空");
        }
        DrugInfo drugInfo = drugService.getDrugInfo(drugNo);
        return ResponseResult.ok().put(drugInfo);
    }

    @ApiOperation(value = "更改药品信息")
    @PostMapping(value = "/update")
    public ResponseResult updateDrugInfo(@RequestBody Drug drug) {
        if(StringUtils.isEmpty(drug.getDrugNo())) {
            return ResponseResult.error("药品no为空");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drug.setUpdateName(user.getUserRealName());
        drug.setUpdateTime(new Date());
        int i = drugService.updateDrugInfo(drug);
        logService.addLog(Action.UPDATE, Table.DRUG, drug.getDrugNo(),i > 0 ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "保存药品信息")
    @PostMapping(value = "/save")
    public ResponseResult saveDrug(@RequestBody Drug drug) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        String drugNo = NoGeneratorUtil.getNo();
        drug.setDrugNo(drugNo);
        drug.setCreateName(user.getUserRealName());
        drug.setCreateTime(new Date());
        boolean save = drugService.save(drug);
        logService.addLog(Action.INSERT, Table.DRUG, drug.getDrugNo(),save ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除药品")
    @PostMapping("/delete")
    public ResponseResult deleteDrug(@RequestBody Drug drug) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行药品删除操作，药品no为{}",user.getUserName(),drug.getDrugNo());
//        drugService.removeById(drug.getId());
        drug.setUpdateName(user.getUserRealName());
        drug.setUpdateTime(new Date());
        drugService.deleteDrug(drug);
        logService.addLog(Action.DELETE, Table.DRUG, drug.getDrugNo());
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取已删除列表")
    @GetMapping("/deleteList")
    public ResponseResult getDeleteList() {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        List<DrugInfo> deleteList = drugService.getDeleteList();
        return ResponseResult.ok().put(deleteList);
    }

    @ApiOperation(value = "恢复被删除的药品")
    @PostMapping("/recover")
    public ResponseResult recoverDrug(@RequestBody Drug drug) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        drug.setUpdateName(user.getUserRealName());
        drug.setUpdateTime(new Date());
        drugService.recoverDrug(drug);
        logService.addLog(Action.RECOVER, Table.DRUG, drug.getDrugNo());
        return ResponseResult.ok();
    }
}
