package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetPatPayRecordByPatNoForm extends ManagePageRequest implements Serializable {



    @ApiModelProperty(value = "orderState", name = "订单状态")
    private String orderState;

    @ApiModelProperty(value = "patNo", name = "患者号，请通过token传递，此处不作参数传递")
    private String patNo;

}
