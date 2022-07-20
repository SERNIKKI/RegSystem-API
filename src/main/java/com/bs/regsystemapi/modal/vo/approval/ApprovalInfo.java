package com.bs.regsystemapi.modal.vo.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/30 17:03
 */
@Data
public class ApprovalInfo implements Serializable {

    private Long id;

    /**
     * 审批单号
     */
    private String approvalNo;

    /**
     * 审批单类型
     */
    private String approvalType;

    /**
     * 标题
     */
    private String approvalTitle;

    /**
     * 审批内容
     */
    private String approvalContact;

    /**
     * 申请人
     */
    private String approvalUser;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /**
     * 处理时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalEndTime;

    /**
     * 处理状态
     */
    private String approvalStatus;

    /**
     * 处理人
     */
    private String approvalName;

    /**
     * 是否已删除
     */
    private String isDelete;

    /**
     * 是否已撤回
     */
    private String isRevoke;

    private String userRealName;

    /**
     * 备注
     */
    private String approvalRemark;

    /**
     * 回执
     */
    private String approvalReply;
}
