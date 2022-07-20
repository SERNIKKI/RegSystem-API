package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/24 18:37
 */
@Data
public class CountForMonthInfo implements Serializable {

    private Integer jan;

    private Integer feb;

    private Integer mar;

    private Integer apr;

    private Integer may;

    private Integer jun;

    private Integer jul;

    private Integer aug;

    private Integer sep;

    private Integer oct;

    private Integer nov;

    private Integer dec;
}
