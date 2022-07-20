package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/6 0:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDoctorHerbsForm extends ManagePageResult implements Serializable {

    private String herbName;

    private String herbPinyin;

    private String herbEfficacy;

    private String subType;

}
