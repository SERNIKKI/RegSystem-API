package com.bs.regsystemapi.modal.dto.patregrecord;

import com.bs.regsystemapi.utils.ManagePageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/5/7 17:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryRegInfoForm extends ManagePageResult implements Serializable {

    private String doctorNo;

    private String userRealName;

    private String secondDepartment;

    private String personName;

    private String patNickName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
