package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/15 22:57
 */
@Data
public class DrugLabelInfo implements Serializable {

    private String label;

    private String value;
}
