package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Approval;
import com.bs.regsystemapi.modal.dto.approval.*;
import com.bs.regsystemapi.modal.vo.approval.ApprovalInfo;
import com.bs.regsystemapi.modal.vo.approval.StatusInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:18
 */

public interface ApprovalService extends IService<Approval> {
    ManagePageResult getApprovalList(QueryApprovalForm form);
    ManagePageResult getUserApprovalList(UserQueryApprovalForm form);
    ApprovalInfo getApprovalInfo(String approvalNo);
    void revokeApproval(ApprovalForm form);
    void updateStatus(UpdateStatus status);
    void deleteApproval(String approvalNo);
    void recoverApproval(String approvalNo);
    StatusInfo getStatusList();
    void sendApproval(Approval approval);
    StatusInfo getUserStatusList(String approvalUser);
    void deleteUserApproval(UserApprovalForm form);
}
