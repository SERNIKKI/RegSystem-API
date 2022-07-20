package com.bs.regsystemapi.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.ApprovalDao;
import com.bs.regsystemapi.entity.Approval;
import com.bs.regsystemapi.entity.Notify;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.approval.*;
import com.bs.regsystemapi.modal.vo.approval.ApprovalInfo;
import com.bs.regsystemapi.modal.vo.approval.StatusInfo;
import com.bs.regsystemapi.service.ApprovalService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.NotifyService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:19
 */
@Service("approvalService")
public class ApprovalServiceImpl extends ServiceImpl<ApprovalDao, Approval> implements ApprovalService {

    @Autowired
    private LogService logService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private NotifyService notifyService;

    @Override
    public ManagePageResult getApprovalList(QueryApprovalForm form) {
        PageHelper.startPage(form.getPageNum(),form.getPageSize());
        List<ApprovalInfo> approvalList = this.baseMapper.getApprovalList(form);
        PageInfo<ApprovalInfo> approvalInfoPageInfo = new PageInfo<>(approvalList);
        return ManagePageResult.getPageResult(approvalInfoPageInfo);
    }

    @Override
    public ManagePageResult getUserApprovalList(UserQueryApprovalForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Approval> userApprovalList = this.baseMapper.getUserApprovalList(form);
        PageInfo<Approval> approvalPageInfo = new PageInfo<>(userApprovalList);
        return ManagePageResult.getPageResult(approvalPageInfo);
    }

    @Override
    public ApprovalInfo getApprovalInfo(String approvalNo) {
        return this.baseMapper.getApprovalInfo(approvalNo);
    }

    @Override
    public void revokeApproval(ApprovalForm form) {
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        form.setApprovalName(user.getUserRealName());
        form.setApprovalEndTime(new Date());
        Integer integer = this.baseMapper.revokeApproval(form);
        if(integer > 0) {
            ApprovalInfo approvalInfo = this.baseMapper.getApprovalInfo(form.getApprovalNo());
            // 编辑通知，发送给用户
            Notify notify = new Notify();
            notify.setNotifyType("审批单回执");
            notify.setNotifyTitle("您的审批单已被撤回");
            // 处理时间
            String date = TimeUtils.formatDate(approvalInfo.getApprovalTime(), "yyyy年MM月dd日 HH时mm分ss秒");
            String content = "<span style='font-size:15px;line-height:1.5em'>您在" + date + "申请的" + approvalInfo.getApprovalType() +
                    "审批单已被管理员<span style='color: #e6a23c;font-weight: 600'>撤回</span></span>";
            content += "。";
            notify.setNotifyContent(content);
            notify.setNotifyNo(NoGeneratorUtil.getNo());
            notify.setNotifyObject(approvalInfo.getApprovalUser());
            notify.setCreateName(user.getUserRealName());
            notify.setSendTime(new Date());
            notify.setSystemNo(NoGeneratorUtil.getNo());
            boolean save = notifyService.save(notify);
            if(save) {
                logService.addLog(Action.INSERT, Table.NOTIFY,notify.getNotifyNo());
            } else {
                logService.addLog(Action.INSERT, Table.NOTIFY,notify.getNotifyNo(), Status.FAIL);
            }
            // 修改状态
            UpdateStatus status = new UpdateStatus();
            status.setApprovalStatus("-1");
            status.setApprovalName(user.getUserRealName());
            status.setApprovalEndTime(new Date());
            status.setApprovalNo(form.getApprovalNo());
            this.baseMapper.updateStatus(status);
            logService.addLog(Action.UPDATE, Table.APPROVAL,form.getApprovalNo());
        }else {
            logService.addLog(Action.UPDATE, Table.APPROVAL,form.getApprovalNo(), Status.FAIL);
        }
    }

    @Override
    public void updateStatus(UpdateStatus status) {
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        status.setApprovalEndTime(new Date());
        status.setApprovalName(user.getUserRealName());
        Integer integer = this.baseMapper.updateStatus(status);
        if(integer > 0) {
            ApprovalInfo approvalInfo = this.baseMapper.getApprovalInfo(status.getApprovalNo());
            // 编辑通知，发送给用户
            Notify notify = new Notify();
            notify.setNotifyType("审批单回执");
            notify.setNotifyTitle("您的审批单有结果啦~");
            // 处理时间
            String date = TimeUtils.formatDate(approvalInfo.getApprovalTime(), "yyyy年MM月dd日 HH时mm分ss秒");
            String content = "<span style='font-size:15px;line-height:1.5em'>" + "您在" + date + "申请的" + approvalInfo.getApprovalType() + "审批单";
            if(status.getApprovalStatus().equals("1")) {
                content += "已<span style='color: #85ce61;font-weight: 600'>通过</span></span>";
            } else if(status.getApprovalStatus().equals("2")) {
                content += "已<span style='color: #F56C6C;font-weight: 600'>被驳回</span></span>";
            }
            content += "。";
            notify.setNotifyContent(content);
            notify.setNotifyNo(NoGeneratorUtil.getNo());
            notify.setNotifyObject(approvalInfo.getApprovalUser());
            notify.setCreateName(user.getUserRealName());
            notify.setSendTime(new Date());
            notify.setSystemNo(NoGeneratorUtil.getNo());
            boolean save = notifyService.save(notify);
            if(save) {
                logService.addLog(Action.INSERT, Table.NOTIFY,notify.getNotifyNo());
            } else {
                logService.addLog(Action.INSERT, Table.NOTIFY,notify.getNotifyNo(), Status.FAIL);
            }
            logService.addLog(Action.UPDATE, Table.APPROVAL,status.getApprovalNo());
        }else {
            logService.addLog(Action.UPDATE, Table.APPROVAL,status.getApprovalNo(), Status.FAIL);
        }
    }

    @Override
    public void deleteApproval(String approvalNo) {
        ApprovalForm approvalForm = new ApprovalForm();
        approvalForm.setApprovalNo(approvalNo);
        approvalForm.setApprovalEndTime(new Date());
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        approvalForm.setApprovalName(user.getUserRealName());
        Integer integer = this.baseMapper.deleteApproval(approvalForm);
        if(integer > 0) {
            logService.addLog(Action.DELETE, Table.APPROVAL,approvalNo);
        }else {
            logService.addLog(Action.DELETE, Table.APPROVAL,approvalNo, Status.FAIL);
        }
    }

    @Override
    public void recoverApproval(String approvalNo) {
        ApprovalForm approvalForm = new ApprovalForm();
        approvalForm.setApprovalNo(approvalNo);
        approvalForm.setApprovalEndTime(new Date());
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        approvalForm.setApprovalName(user.getUserRealName());
        Integer integer = this.baseMapper.recoverApproval(approvalForm);
        if(integer > 0) {
            logService.addLog(Action.RECOVER, Table.APPROVAL,approvalNo);
        }else {
            logService.addLog(Action.RECOVER, Table.APPROVAL,approvalNo, Status.FAIL);
        }
    }

    @Override
    public StatusInfo getStatusList() {
        return this.baseMapper.getStatusList();
    }

    @Override
    public void sendApproval(Approval approval) {
        approval.setApprovalNo(NoGeneratorUtil.getNo());
        approval.setApprovalTime(new Date());
        boolean save = approvalService.save(approval);
        if(save) {
            logService.addLog(Action.INSERT, Table.APPROVAL,approval.getApprovalNo());
        }else {
            logService.addLog(Action.INSERT, Table.APPROVAL,approval.getApprovalNo(), Status.FAIL);
        }
    }

    @Override
    public StatusInfo getUserStatusList(String approvalUser) {
        return this.baseMapper.getUserStatusList(approvalUser);
    }

    @Override
    public void deleteUserApproval(UserApprovalForm form) {
        Integer integer = this.baseMapper.deleteUserApproval(form);
    }
}
