package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetPatPayRecordByPersonNoForm extends ManagePageRequest implements Serializable {

    @ApiModelProperty(value = "personNo", name = "就诊卡号",required = true)
    @NotNull(message = "就诊卡号不可为空!")
    private String personNo;
}
