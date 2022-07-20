package com.bs.regsystemapi.modal.vo.prescription;

import com.bs.regsystemapi.entity.PrescriptionHerbs;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/8 18:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class prescriptionHerbsInfo extends PrescriptionHerbs implements Serializable {

    private String drugName;

    private String mainType;

    private String subType;

    private String indication;

    private String genus;

    private String spec;

    private String usage;

    private String drugPackage;

    private String drugUnit;
}
