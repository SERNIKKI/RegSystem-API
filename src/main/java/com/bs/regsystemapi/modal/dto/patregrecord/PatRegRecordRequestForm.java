package com.bs.regsystemapi.modal.dto.patregrecord;

import com.bs.regsystemapi.utils.ManagePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2022/4/27 16:58
 */
@Data
public class PatRegRecordRequestForm extends ManagePageRequest implements Serializable {

    /**
     * 医生姓名
     */
    @ApiModelProperty(value = "userRealName", name = "医生姓名")
    private String userRealName;

    /**
     * 预约日期（年-月-日）
     */
    @ApiModelProperty(value = "visitDate", name = "预约日期（年-月-日）")
    private String visitDate;

    /**
     * 所属科室
     */
    @ApiModelProperty(value = "userDepartment", name = "所属科室")
    private String userDepartment;
}
