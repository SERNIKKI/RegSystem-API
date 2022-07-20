package com.bs.regsystemapi.modal.dto.prescription;

import com.bs.regsystemapi.utils.ManagePageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/5/8 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPrescriptionForm extends ManagePageResult implements Serializable {

    private String userRealName;

    private String secondDepartment;

    private String personName;

    private String personAddress;

    private String diagnosis;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String doctorNo;

}
