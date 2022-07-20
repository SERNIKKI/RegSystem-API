package com.bs.regsystemapi.modal.dto.approval;

import com.bs.regsystemapi.utils.ManagePageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/4/27 10:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryApprovalForm extends ManagePageRequest implements Serializable {

    private String approvalUser;

    private String approvalType;

    private String approvalContact;

    private String approvalStatus;

    private String isRevoke;

}
