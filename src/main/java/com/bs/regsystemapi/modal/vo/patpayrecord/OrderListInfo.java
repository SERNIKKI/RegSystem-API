package com.bs.regsystemapi.modal.vo.patpayrecord;

import com.bs.regsystemapi.entity.PatPayRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/5/10 17:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListInfo extends PatPayRecord implements Serializable {

    private String personName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitData;

    private String illnessDetail;

    private String userRealName;

    private String secondDepartment;

    private String personPhone;
}
