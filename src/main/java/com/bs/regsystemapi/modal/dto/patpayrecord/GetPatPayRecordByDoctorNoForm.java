package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetPatPayRecordByDoctorNoForm extends ManagePageRequest implements Serializable {

    @ApiModelProperty(value = "doctorNo", name = "医生No", required = true)
    @NotNull(message = "医生No不可为空")
    private String doctorNo;
}
