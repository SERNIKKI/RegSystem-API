package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.DrugProd;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugProdForm;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.service.DrugProdService;
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
 * @date 2022/3/16 11:22
 */
@RestController
@RequestMapping("/drugProd")
@Api(value = "药物生产企业接口")
public class DrugProdController {

    private static final Logger logger = LoggerFactory.getLogger(DrugProdController.class);

    @Autowired
    private DrugProdService drugProdService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取公司名称与公司no")
    @GetMapping("/label")
    public ResponseResult getDrugProdLabel() {
        List<DrugLabelInfo> drugProdLabel = drugProdService.getDrugProdLabel();
        return ResponseResult.ok().put(drugProdLabel);
    }

    @ApiOperation(value = "获取企业详情")
    @GetMapping(value = "/info/{prodNo}")
    public ResponseResult getDrugProdInfo(@PathVariable("prodNo") String prodNo) {
        if(StringUtils.isEmpty(prodNo)) {
            return ResponseResult.error("企业no不能为空");
        }
        DrugProd drugProdInfo = drugProdService.getDrugProdInfo(prodNo);
        return ResponseResult.ok().put(drugProdInfo);
    }

    @ApiOperation(value = "获取生产企业列表")
    @PostMapping(value = "/list")
    public ResponseResult getDrugProdList(@RequestBody QueryDrugProdForm form) {
        ManagePageResult drugProdList2 = drugProdService.getDrugProdList2(form);
        return ResponseResult.ok().put(drugProdList2);
    }

    @ApiOperation(value = "更改生产企业信息")
    @PostMapping(value = "/update")
    public ResponseResult updateDrugProdInfo(@RequestBody DrugProd drugProd) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("未登录，请登录！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugProd.setUpdateName(user.getUserRealName());
        drugProd.setUpdateTime(new Date());
        int i = drugProdService.updateDrugProdInfo(drugProd);
        logService.addLog(Action.UPDATE, Table.DRUG_PROD, drugProd.getProdNo(),i > 0 ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "新增企业")
    @PostMapping(value = "/save")
    public ResponseResult saveDrugInfo(@RequestBody DrugProd drugProd) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("未登录，请登录！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugProd.setProdNo(NoGeneratorUtil.getNo());
        drugProd.setCreateName(user.getUserRealName());
        drugProd.setCreateTime(new Date());
        boolean save = drugProdService.save(drugProd);
        logService.addLog(Action.UPDATE, Table.DRUG_PROD, drugProd.getProdNo(),save ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除制造企业")
    @PostMapping("/delete")
    public ResponseResult deleteProd(@RequestBody DrugProd drugProd) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行制造企业删除操作，企业no为{}",user.getUserName(),drugProd.getProdNo());
//        drugProdService.removeById(drugProd.getId());
        drugProd.setUpdateTime(new Date());
        drugProd.setUpdateName(user.getUserRealName());
        drugProdService.deleteProd(drugProd);
        logService.addLog(Action.DELETE, Table.DRUG_PROD, drugProd.getProdNo());
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
        List<DrugProd> deleteList = drugProdService.getDeleteList();
        return ResponseResult.ok().put(deleteList);
    }

    @ApiOperation(value = "恢复被删除的企业")
    @PostMapping("/recover")
    public ResponseResult recoverProd(@RequestBody DrugProd drugProd) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        drugProd.setUpdateTime(new Date());
        drugProd.setUpdateName(user.getUserRealName());
        drugProdService.recoverProd(drugProd);
        logService.addLog(Action.RECOVER, Table.DRUG_PROD, drugProd.getProdNo());
        return ResponseResult.ok();
    }
}
