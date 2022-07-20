package com.bs.regsystemapi.modal.vo.drug;

import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 23:14
 */
@Data
public class DrugTypeInfo implements Serializable {

    private String label;

    private String value;

    private List<DrugLabelInfo> children;
}
