package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Approval;
import com.bs.regsystemapi.modal.dto.approval.*;
import com.bs.regsystemapi.modal.vo.approval.ApprovalInfo;
import com.bs.regsystemapi.modal.vo.approval.StatusInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:16
 */
@Mapper
public interface ApprovalDao extends BaseMapper<Approval> {
    List<ApprovalInfo> getApprovalList(QueryApprovalForm form);
    List<Approval> getUserApprovalList(UserQueryApprovalForm form);
    ApprovalInfo getApprovalInfo(String approvalNo);
    Integer revokeApproval(ApprovalForm form);
    Integer updateStatus(UpdateStatus status);
    Integer deleteApproval(ApprovalForm form);
    Integer recoverApproval(ApprovalForm form);
    StatusInfo getStatusList();
    StatusInfo getUserStatusList(String approvalUser);
    Integer deleteUserApproval(UserApprovalForm form);
}
