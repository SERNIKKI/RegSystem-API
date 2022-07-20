package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/15 15:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDrugForm extends ManagePageResult implements Serializable {

    private String drugName;

    private String drugGeneralName;

    private String drugIndication;

    private String drugAction;

    private String drugCompany;

    private String createName;

    private String drugGenus;
}
