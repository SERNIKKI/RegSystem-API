package com.bs.regsystemapi.modal.dto.patpayrecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PatPayRecordDetail implements Serializable {

    @ApiModelProperty(name = "doctorNo", value = "医生No", required = true)
    @NotNull(message = "医生No不可为空!")
    private String doctorNo;

    @ApiModelProperty(name = "orderNo", value = "订单号", required = true)
    @NotNull(message = "订单号不可为空!")
    private String orderNo;

}
