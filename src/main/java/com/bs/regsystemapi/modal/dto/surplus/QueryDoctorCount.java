package com.bs.regsystemapi.modal.dto.surplus;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/4/27 16:59
 */
@Data
public class QueryDoctorCount implements Serializable {

    private String doctorNo;

    private Date date;

}
