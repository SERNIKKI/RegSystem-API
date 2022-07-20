package com.bs.regsystemapi.modal.dto.patregrecord;

import com.bs.regsystemapi.utils.ManagePageResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PatRegRecordListForm extends ManagePageResult implements Serializable {

    @ApiModelProperty(value = "doctorNo", name = "医生No", required = true)
    @NotNull(message = "医生No不可为空!")
    private String doctorNo;
    @ApiModelProperty(value = "visitDate", name = "预约日期，默认当天", required = false)
    private String visitDate;

}
