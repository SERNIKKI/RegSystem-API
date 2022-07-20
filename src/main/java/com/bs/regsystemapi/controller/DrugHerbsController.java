package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.DrugHerbs;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.QueryDoctorHerbsForm;
import com.bs.regsystemapi.modal.dto.drug.QueryHerbsForm;
import com.bs.regsystemapi.modal.vo.drug.HerbInfo;
import com.bs.regsystemapi.service.DrugHerbsService;
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
 * @date 2022/3/17 10:58
 */
@RestController
@RequestMapping("/drugHerbs")
@Api(value = "中药->中药材接口")
public class DrugHerbsController {

    private static final Logger logger = LoggerFactory.getLogger(DrugHerbsController.class);

    @Autowired
    private DrugHerbsService drugHerbsService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取中药材列表")
    @PostMapping("/list")
    public ResponseResult getHerbList(@RequestBody QueryHerbsForm form) {
        ManagePageResult herbList = drugHerbsService.getHerbList(form);
        return ResponseResult.ok().put(herbList);
    }

    @ApiOperation(value = "获取中药材列表")
    @PostMapping("/v2/list")
    public ResponseResult getHerbList(@RequestBody QueryDoctorHerbsForm form) {
        ManagePageResult herbList = drugHerbsService.getHerbs(form);
        return ResponseResult.ok().put(herbList);
    }

    @ApiOperation(value = "获取中药材详情")
    @GetMapping(value = "/info/{herbNo}")
    public ResponseResult getHerbInfo(@PathVariable("herbNo") String herbNo) {
        if(StringUtils.isEmpty(herbNo)) {
            return ResponseResult.error("中药材编号不能为空！");
        }
        HerbInfo herbInfo = drugHerbsService.getHerbInfo(herbNo);
        return ResponseResult.ok().put(herbInfo);
    }

    @ApiOperation(value = "修改中药材信息")
    @PostMapping("/update")
    public ResponseResult updateHerbInfo(@RequestBody DrugHerbs drugHerbs) {
        if(StringUtils.isEmpty(drugHerbs.getHerbNo())) {
            return ResponseResult.error("中药材编号不能为空！");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugHerbs.setUpdateName(user.getUserRealName());
        drugHerbs.setUpdateTime(new Date());
        int i = drugHerbsService.updateHerbInfo(drugHerbs);
        logService.addLog(Action.UPDATE, Table.DRUG_HERBS, drugHerbs.getHerbNo(),i > 0 ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "新增中药材")
    @PostMapping("/save")
    public ResponseResult saveHerbInfo(@RequestBody DrugHerbs drugHerbs) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugHerbs.setHerbNo(NoGeneratorUtil.getNo());
        drugHerbs.setCreateName(user.getUserRealName());
        drugHerbs.setCreateTime(new Date());
        boolean save = drugHerbsService.save(drugHerbs);
        logService.addLog(Action.INSERT, Table.DRUG_HERBS, drugHerbs.getHerbNo(),save ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除中药材")
    @PostMapping("/delete")
    public ResponseResult deleteHerb(@RequestBody DrugHerbs drugHerbs) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行中药材删除操作，药品no为{}",user.getUserName(),drugHerbs.getHerbNo());
//        drugHerbsService.removeById(drugHerbs.getId());
        drugHerbs.setUpdateTime(new Date());
        drugHerbs.setUpdateName(user.getUserRealName());
        drugHerbsService.deleteHerb(drugHerbs);
        logService.addLog(Action.DELETE, Table.DRUG_HERBS, drugHerbs.getHerbNo());
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
        List<HerbInfo> deleteList = drugHerbsService.getDeleteList();
        return ResponseResult.ok().put(deleteList);
    }

    @ApiOperation(value = "恢复被删除的中药材")
    @PostMapping("/recover")
    public ResponseResult recoverHerb(@RequestBody DrugHerbs drugHerbs) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        drugHerbs.setUpdateTime(new Date());
        drugHerbs.setUpdateName(user.getUserRealName());
        drugHerbsService.recoverHerb(drugHerbs);
        logService.addLog(Action.RECOVER, Table.DRUG_HERBS, drugHerbs.getHerbNo());
        return ResponseResult.ok();
    }
}
