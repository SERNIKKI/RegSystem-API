package com.bs.regsystemapi.modal.dto.patient;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetPatientListForm extends ManagePageResult implements Serializable {

    /**
     * 患者真实姓名
     */
    private String patRealName;

}
