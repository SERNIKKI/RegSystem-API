package com.bs.regsystemapi.modal.vo.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/24 18:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockMonthInfo implements Serializable {

    private List<Long> data;

    private String name;

    private String itemStyle;

}
