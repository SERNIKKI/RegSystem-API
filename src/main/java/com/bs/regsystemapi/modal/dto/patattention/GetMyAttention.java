package com.bs.regsystemapi.modal.dto.patattention;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2022/4/30 19:18
 */
@Data
public class GetMyAttention implements Serializable {

    @ApiModelProperty(name = "doctorNo", value = "医生No", required = false)
    private String doctorNo;

}
