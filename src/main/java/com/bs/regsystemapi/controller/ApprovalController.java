package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Approval;
import com.bs.regsystemapi.modal.dto.approval.*;
import com.bs.regsystemapi.modal.vo.approval.ApprovalInfo;
import com.bs.regsystemapi.modal.vo.approval.StatusInfo;
import com.bs.regsystemapi.service.ApprovalService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:20
 */
@RestController
@RequestMapping("/approval")
@Api(value = "审批单相关接口")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @ApiOperation(value = "获取审理单列表")
    @PostMapping("/list")
    public ResponseResult getApprovalList(@RequestBody QueryApprovalForm form){
        ManagePageResult approvalList = approvalService.getApprovalList(form);
        return ResponseResult.ok().put(approvalList);
    }

    @ApiOperation(value = "用户获取审批单列表")
    @PostMapping("/userList")
    public ResponseResult getUserApprovalList(@RequestBody UserQueryApprovalForm form) {
        if(StringUtils.isEmpty(form.getApprovalUser())) {
            return ResponseResult.error("用户no不能为空");
        }
        ManagePageResult userApprovalList = approvalService.getUserApprovalList(form);
        return ResponseResult.ok().put(userApprovalList);
    }

    @ApiOperation(value = "获取审批单详情")
    @GetMapping("/info/{approvalNo}")
    public ResponseResult getApprovalInfo(@PathVariable("approvalNo") String approvalNo) {
        if(StringUtils.isEmpty(approvalNo)) {
            return ResponseResult.error("审批单no为空!");
        }
        ApprovalInfo approvalInfo = approvalService.getApprovalInfo(approvalNo);
        return ResponseResult.ok().put(approvalInfo);
    }

    @ApiOperation(value = "撤回审批单")
    @PostMapping("/revoke")
    @Transactional
    public ResponseResult revokeApproval(@RequestBody ApprovalForm form) {
        if(StringUtils.isEmpty(form.getApprovalNo())) {
            return ResponseResult.error("审批单no为空!");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        approvalService.revokeApproval(form);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "管理员处理审批单")
    @PostMapping("/updateStatus")
    @Transactional
    public ResponseResult updateStatus(@RequestBody UpdateStatus status) {
        if(StringUtils.isEmpty(status.getApprovalNo())) {
            return ResponseResult.error("审批单no为空!");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        approvalService.updateStatus(status);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除审批单")
    @GetMapping("/delete/{approvalNo}")
    public ResponseResult deleteApproval(@PathVariable("approvalNo") String approvalNo) {
        if(StringUtils.isEmpty(approvalNo)) {
            return ResponseResult.error("审批单no为空!");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        approvalService.deleteApproval(approvalNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "恢复审批单")
    @GetMapping("/recover/{approvalNo}")
    public ResponseResult recoverApproval(@PathVariable("approvalNo") String approvalNo) {
        if(StringUtils.isEmpty(approvalNo)) {
            return ResponseResult.error("审批单no为空!");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        approvalService.recoverApproval(approvalNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取各种状态的审批单数量")
    @GetMapping("/status")
    public ResponseResult getStatusList() {
        StatusInfo statusList = approvalService.getStatusList();
        return ResponseResult.ok().put(statusList);
    }

    @ApiOperation(value = "发起审批单")
    @PostMapping("/send")
    public ResponseResult sendApproval(@RequestBody Approval approval) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败!");
        }
        approvalService.sendApproval(approval);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "用户获取各种状态的审批单数量")
    @GetMapping("/status/{approvalUser}")
    public ResponseResult getUserStatusList(@PathVariable("approvalUser") String approvalUser) {
        StatusInfo statusList = approvalService.getUserStatusList(approvalUser);
        return ResponseResult.ok().put(statusList);
    }

    @ApiOperation(value = "用户删除审批单")
    @PostMapping("/delete")
    public ResponseResult deleteApproval(@RequestBody UserApprovalForm form) {
        if(StringUtils.isEmpty(form.getApprovalNo())) {
            return ResponseResult.error("审批单no为空!");
        }
        if(StringUtils.isEmpty(form.getApprovalUser())) {
            return ResponseResult.error("用户no为空!");
        }
        approvalService.deleteUserApproval(form);
        return ResponseResult.ok();
    }
}
