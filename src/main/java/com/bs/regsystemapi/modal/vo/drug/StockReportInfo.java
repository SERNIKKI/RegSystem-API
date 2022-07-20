package com.bs.regsystemapi.modal.vo.drug;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/24 17:27
 */
@Data
public class StockReportInfo implements Serializable {

    private String name;

    private Long value;

    private String itemStyle;

}
