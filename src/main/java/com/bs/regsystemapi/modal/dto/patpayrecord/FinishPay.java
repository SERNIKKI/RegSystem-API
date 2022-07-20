package com.bs.regsystemapi.modal.dto.patpayrecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FinishPay implements Serializable {

    @ApiModelProperty(name = "orderNo", value = "订单号", required = true)
    @NotNull(message = "订单号不可为空!")
    private String orderNo;
    @ApiModelProperty(name = "payType", value = "付款类型", required = true)
    @NotNull(message = "付款类型不可为空!")
    private String payType;
    @ApiModelProperty(name = "payMode", value = "支付方式", required = true)
    @NotNull(message = "支付方式不可为空!")
    private String payMode;
    @ApiModelProperty(name = "personNo", value = "就诊卡号", required = true)
    @NotNull(message = "就诊卡号不可为空!")
    private String personNo;

}
