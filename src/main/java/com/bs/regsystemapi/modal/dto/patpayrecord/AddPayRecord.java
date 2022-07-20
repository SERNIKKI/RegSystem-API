package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class AddPayRecord implements Serializable {

    @ApiModelProperty(name = "doctorNo", value = "医生no", required = true)
    @NotNull(message = "医生no不可为空!")
    private String doctorNo;

    @ApiModelProperty(name = "regNo", value = "预约号", required = true)
    @NotNull(message = "预约号不可为空!")
    private String regNo;

    @ApiModelProperty(name = "personNo", value = "就诊卡号", required = true)
    @NotNull(message = "就诊卡号不可为空!")
    private String personNo;

    @ApiModelProperty(name = "orderState", value = "订单状态，0待付款1已付款", required = false)
    private String orderState;

    @ApiModelProperty(name = "orderNo", value = "订单号", required = false)
    private String orderNo;

    @ApiModelProperty(name = "creatTime", value = "订单创建时间", required = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    @ApiModelProperty(name = "isDelete", value = "是否已删除，1：正常，0：已删除", required = false)
    private String isDelete;

}
