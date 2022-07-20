package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/21 16:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPriceForm extends ManagePageResult implements Serializable {

    private String drugName;

    private String drugType;

    private String isInsurance;
}
