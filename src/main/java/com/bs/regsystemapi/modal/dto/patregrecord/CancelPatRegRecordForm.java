package com.bs.regsystemapi.modal.dto.patregrecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Date 2022/4/27 22:29
 */
@Data
public class CancelPatRegRecordForm implements Serializable {

    @ApiModelProperty(name = "regNo", value = "预约号", required = true)
    @NotBlank(message = "预约号不能为空!")
    private String regNo;


}
