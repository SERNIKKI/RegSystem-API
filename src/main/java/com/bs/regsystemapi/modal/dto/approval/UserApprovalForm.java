package com.bs.regsystemapi.modal.dto.approval;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/4/27 11:18
 */
@Data
public class UserApprovalForm implements Serializable {

    private String approvalNo;

    private String approvalUser;

}
