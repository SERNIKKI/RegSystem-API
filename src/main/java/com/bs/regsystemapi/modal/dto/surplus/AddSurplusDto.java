package com.bs.regsystemapi.modal.dto.surplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qpj
 * @date 2022/4/20 11:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSurplusDto {

    private String doctorNo;

    private String colum;

    private String morning;

    private String afternoon;

    private String night;

    public AddSurplusDto(String doctorNo, String colum) {
        this.doctorNo = doctorNo;
        this.colum = colum;
        this.morning = colum + "_morning";
        this.afternoon = colum + "_afternoon";
        this.night = colum + "_night";
    }
}
