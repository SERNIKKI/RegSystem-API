package com.bs.regsystemapi.modal.dto.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/24 19:23
 */
@Data
public class QueryEveryMonthReportForm implements Serializable {

    private String drugType;

    private String sort;

    private int limit;
}
