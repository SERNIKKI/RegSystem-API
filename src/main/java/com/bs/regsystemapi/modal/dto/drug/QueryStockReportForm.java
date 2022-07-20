package com.bs.regsystemapi.modal.dto.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/24 17:29
 */
@Data
public class QueryStockReportForm implements Serializable {

    private String drugType;

    private int month;

    private String sort;

    private int limit;
}
