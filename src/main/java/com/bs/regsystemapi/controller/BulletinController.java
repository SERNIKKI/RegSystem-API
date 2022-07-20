package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Bulletin;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.bulletin.QueryBulletinForm;
import com.bs.regsystemapi.service.BulletinService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:23
 */
@RestController
@RequestMapping("/bulletin")
@Api(value = "公告相关接口")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取公告列表")
    @PostMapping("/list")
    public ResponseResult getBulletinList(@RequestBody QueryBulletinForm form) {
        ManagePageResult bulletinList = bulletinService.getBulletinList(form);
        return ResponseResult.ok().put(bulletinList);
    }

    @ApiOperation(value = "首页获取公告列表")
    @GetMapping("/validList")
    public ResponseResult getValidBulletin() {
        List<Bulletin> validBulletin = bulletinService.getValidBulletin();
        return ResponseResult.ok().put(validBulletin);
    }

    @ApiOperation(value = "获取公告详情")
    @GetMapping("/info/{bulletinNo}")
    private ResponseResult getBulletinInfo(@PathVariable("bulletinNo") String bulletinNo) {
        if(StringUtils.isEmpty(bulletinNo)) {
            return ResponseResult.error("公告no不能为空");
        }
        Bulletin bulletinInfo = bulletinService.getBulletinInfo(bulletinNo);
        return ResponseResult.ok().put(bulletinInfo);
    }

    @ApiOperation(value = "删除公告")
    @GetMapping("/delete/{bulletinNo}")
    public ResponseResult deleteBulletin(@PathVariable("bulletinNo") String bulletinNo) {
        if(StringUtils.isEmpty(bulletinNo)) {
            return ResponseResult.error("公告no不能为空");
        }
        Integer integer = bulletinService.deleteBulletin(bulletinNo);
        if(integer > 0) {
            logService.addLog(Action.DELETE, Table.BULLETIN,bulletinNo);
        } else {
            logService.addLog(Action.DELETE, Table.BULLETIN,bulletinNo, Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "恢复被删除的公告")
    @GetMapping("/recover/{bulletinNo}")
    public ResponseResult recoverBulletin(@PathVariable("bulletinNo") String bulletinNo) {
        if(StringUtils.isEmpty(bulletinNo)) {
            return ResponseResult.error("公告no不能为空");
        }
        Integer integer = bulletinService.recoverBulletin(bulletinNo);
        if(integer > 0) {
            logService.addLog(Action.RECOVER, Table.BULLETIN,bulletinNo);
        } else {
            logService.addLog(Action.RECOVER, Table.BULLETIN,bulletinNo, Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "新增公告")
    @PostMapping("/save")
    public ResponseResult saveBulletin(@RequestBody Bulletin bulletin) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        bulletin.setUpdateName(user.getUserRealName());
        bulletin.setBulletinName(user.getUserRealName());
        bulletin.setUpdateTime(new Date());
        bulletin.setBulletinNo(NoGeneratorUtil.getNo());
        boolean save = bulletinService.save(bulletin);
        if(save) {
            logService.addLog(Action.INSERT, Table.BULLETIN,bulletin.getBulletinNo());
        } else {
            logService.addLog(Action.INSERT, Table.BULLETIN,bulletin.getBulletinNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "更改公告")
    @PostMapping("/update")
    public ResponseResult updateBulletin(@RequestBody Bulletin bulletin) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        if(StringUtils.isEmpty(bulletin.getBulletinNo())) {
            return ResponseResult.error("公告no不能为空");
        }
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        bulletin.setUpdateName(user.getUserRealName());
        bulletin.setUpdateTime(new Date());
        boolean b = bulletinService.updateById(bulletin);
        if(b) {
            logService.addLog(Action.UPDATE, Table.BULLETIN,bulletin.getBulletinNo());
        } else {
            logService.addLog(Action.UPDATE, Table.BULLETIN,bulletin.getBulletinNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }
}
