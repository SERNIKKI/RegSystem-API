package com.bs.regsystemapi.modal.dto.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/22 15:47
 */
@Data
public class QueryStockForMonthForm implements Serializable {

    private int month;

    private String drugNo;
}
