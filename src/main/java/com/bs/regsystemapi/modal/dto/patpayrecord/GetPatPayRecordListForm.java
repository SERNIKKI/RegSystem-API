package com.bs.regsystemapi.modal.dto.patpayrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetPatPayRecordListForm extends ManagePageRequest implements Serializable {

    /**
     * 医生姓名
     */
    @ApiModelProperty(value = "userRealName", name = "医生姓名")
    private String userRealName;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "patRealName", name = "患者姓名")
    private String patRealName;

    /**
     * 科室
     */
    @ApiModelProperty(value = "userDepartment", name = "科室")
    private String userDepartment;
}
