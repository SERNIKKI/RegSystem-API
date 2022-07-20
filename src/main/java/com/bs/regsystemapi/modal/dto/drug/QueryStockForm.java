package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/22 15:18
 */
@Data
public class QueryStockForm extends ManagePageResult implements Serializable {

    private String drugName;

    private String drugType;
}
