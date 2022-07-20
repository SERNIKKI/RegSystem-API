package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/16 11:03
 */
@Data
public class QueryDrugProdForm extends ManagePageResult implements Serializable {

    private String prodName;
    
    private String prodProvince;
}
