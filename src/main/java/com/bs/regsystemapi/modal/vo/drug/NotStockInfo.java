package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/22 15:15
 */
@Data
public class NotStockInfo implements Serializable {
    private String drugNo;

    private String drugName;

    private String drugType;
}
