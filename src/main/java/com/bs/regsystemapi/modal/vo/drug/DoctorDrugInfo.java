package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/4 17:50
 */
@Data
public class DoctorDrugInfo implements Serializable {

    private String drugNo;

    private String drugGeneralName;

    private String drugNature;

    private String drugIndication;

    private String drugSpec;

    private String drugUsage;

    private String drugPackage;

    private String subType;

    private String drugPrice;

    private String drugPriceInsurance;

    private String drugUnit;

    private String drugGenus;

    private Long countTotal;

    private int num = 0;

    private String isInsurance;

}
