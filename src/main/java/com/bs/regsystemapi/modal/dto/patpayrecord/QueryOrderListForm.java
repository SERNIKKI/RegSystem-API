package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/5/10 17:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderListForm extends ManagePageResult implements Serializable {

    private String personName;

    private String userRealName;

    private String secondDepartment;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String doctorNo;

}
