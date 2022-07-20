package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetPatPayRecordByOrderNoForm extends ManagePageRequest implements Serializable {

    @ApiModelProperty(value = "orderNo", name = "订单号",required = true)
    @NotNull(message = "订单号不可为空")
    private String orderNo;

}
