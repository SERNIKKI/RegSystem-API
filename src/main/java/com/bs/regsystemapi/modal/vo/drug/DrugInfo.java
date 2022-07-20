package com.bs.regsystemapi.modal.vo.drug;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/16 10:36
 */
@Data
public class DrugInfo implements Serializable {

    private Long id;

    private String drugName;

    private String drugGeneralName;

    private String drugIngredient;

    private String drugNature;

    private String drugAction;

    private String drugNo;

    private String drugIndication;

    private String drugSpec;

    private String drugUsage;

    private String drugAdverse;

    private String drugTaboo;

    private String drugCautions;

    private String drugInteraction;

    private String drugPharEffects;

    private String drugStorage;

    private String drugPackage;

    private String drugPeriodDate;

    private String drugStandards;

    private String drugApprovalNum;

    private String drugCompany;

    private Long drugPrice;

    private String updateName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String mainType;

    private String subType;

    private String prodName;

    private String drugGenus;

    private String drugImage;
}
