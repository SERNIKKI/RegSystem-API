package com.bs.regsystemapi.modal.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestInfo extends BaseInfo implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * No
     */
    private String testNo;

    /**
     * 备注
     */
    private String testRemark;
}
