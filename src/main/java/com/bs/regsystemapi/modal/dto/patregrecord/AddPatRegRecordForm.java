package com.bs.regsystemapi.modal.dto.patregrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2022/4/30 20:29
 */
@Data
public class AddPatRegRecordForm implements Serializable {


    /**
     * 就诊卡号
     */
    @ApiModelProperty(name = "personNo", value = "就诊卡号", required = true)
    @NotNull(message = "就诊卡号不可为空!")
    private String personNo;

    /**
     * 医生no
     */
    @ApiModelProperty(name = "doctorNo", value = "医生no", required = true)
    @NotNull(message = "医生no不可为空!")
    private String doctorNo;

    /**
     * 就诊时间
     */
    @ApiModelProperty(name = "visitTime", value = "就诊时间", required = true)
    @NotNull(message = "就诊时间不可为空!")
    private String visitTime;

    /**
     * 疾病描述
     */
    @ApiModelProperty(name = "illnessDetail", value = "疾病描述", required = true)
    @NotNull(message = "疾病描述不可为空!")
    private String illnessDetail;


    /**
     * 就诊日期
     */
    @ApiModelProperty(name = "visitData", value = "就诊日期", required = true)
    @NotNull(message = "就诊日期不可为空!")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitData;


    /**
     * 患者备注
     */
    @ApiModelProperty(name = "personRemark", value = "患者备注", required = false)
    private String personRemark;

}
