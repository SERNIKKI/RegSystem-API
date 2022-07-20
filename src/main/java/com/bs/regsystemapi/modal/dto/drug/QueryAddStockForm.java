package com.bs.regsystemapi.modal.dto.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/22 16:31
 */
@Data
public class QueryAddStockForm implements Serializable {

    private String drugNo;

    private int count;
}
