package com.bs.regsystemapi.modal.dto.patient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Date 2022/4/24 21:19
 */
@Data
public class PatientRequestForm implements Serializable {

    @ApiModelProperty(name = "patAccount", value = "患者账号", required = true)
    @NotBlank(message = "患者账号不可为空!")
    private String patAccount;

    @ApiModelProperty(name = "patPassword", value = "患者密码", required = true)
    @NotBlank(message = "患者密码不可为空!")
    private String patPassword;

}
