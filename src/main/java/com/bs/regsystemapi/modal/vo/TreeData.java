package com.bs.regsystemapi.modal.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeData implements Serializable {

    private String label;

    private String value;

    private List<TreeData> children;

}
