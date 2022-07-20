package com.bs.regsystemapi.modal.dto.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/30 16:53
 */
@Data
public class UpdateStatus implements Serializable {

    private String approvalNo;

    private String approvalStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalEndTime;

    private String approvalName;

    private String approvalRemark;

    private String approvalReply;

}
