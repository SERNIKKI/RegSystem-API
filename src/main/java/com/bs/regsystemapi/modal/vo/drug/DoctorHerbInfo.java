package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/6 0:08
 */
@Data
public class DoctorHerbInfo implements Serializable {

    private String herbNo;

    private String herbName;

    private String herbPinyin;

    private String herbEfficacy;

    private String herbUsage;

    private String subType;

    private String drugPrice;

    private String drugPriceInsurance;

    private String drugUnit;

    private long countTotal;

    private String isInsurance;

    private int num = 0;
}
