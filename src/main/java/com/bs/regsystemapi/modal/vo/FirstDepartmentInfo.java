package com.bs.regsystemapi.modal.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/14 17:06
 */
@Data
public class FirstDepartmentInfo implements Serializable {

    private String department;

    private String firstPerson;

    private String firstTel;

    private String firstAddress;

    private String firstNo;
}
