package com.bs.regsystemapi.modal.dto.doctor;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/25 12:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDoctorForm extends ManagePageResult implements Serializable {

    private String userRealName;

    private String userDepartment;

    private String userPosition;
}
