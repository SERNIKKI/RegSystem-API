package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/4 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDrugListForm extends ManagePageResult implements Serializable {

    private String drugGeneralName;

    private String drugIndication;

    private String subType;

    private String drugGenus;

}
