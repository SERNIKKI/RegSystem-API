package com.bs.regsystemapi.modal.dto.doctor;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/25 12:16
 */
@Data
public class QueryDoctorsForm implements Serializable {

    private String userDepartment;

    private String userPosition;
}
