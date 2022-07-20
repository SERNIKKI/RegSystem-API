package com.bs.regsystemapi.modal.dto.approval;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/30 16:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryApprovalForm extends ManagePageResult implements Serializable {

    private String approvalType;

    private String approvalTitle;

    private String approvalStatus;

    private String isRevoke;

    private String beginTime;

    private String endTime;
}
