package com.bs.regsystemapi.modal.vo.Duty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/4/20 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DutyList implements Serializable {

    private String time;

    private String mon;

    private String tue;

    private String wed;

    private String thur;

    private String fir;

    private String sat;

    private String sun;
}
