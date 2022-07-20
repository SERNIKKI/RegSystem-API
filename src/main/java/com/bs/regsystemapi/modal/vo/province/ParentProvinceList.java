package com.bs.regsystemapi.modal.vo.province;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/4/21 16:58
 */
@Data
public class ParentProvinceList implements Serializable {

    private String label;

    private String value;

    private List<ChildProvinceList> children;
}
