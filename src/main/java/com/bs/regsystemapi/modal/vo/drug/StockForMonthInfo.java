package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/22 16:01
 */
@Data
public class StockForMonthInfo implements Serializable {

    private String drugNo;

    private String drugType;

    private String drugName;

    private String drugUnit;

    private int month;

    private int countTotal;

    private int countSold;

    private int count;
}
