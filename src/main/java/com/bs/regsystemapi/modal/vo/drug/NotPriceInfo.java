package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/21 14:44
 */
@Data
public class NotPriceInfo implements Serializable {

    private String drugNo;

    private String drugName;

    private String drugType;
}
