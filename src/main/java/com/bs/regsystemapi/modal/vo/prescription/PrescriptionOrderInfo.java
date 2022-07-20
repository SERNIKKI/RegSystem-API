package com.bs.regsystemapi.modal.vo.prescription;

import com.bs.regsystemapi.entity.PrescriptionOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/9 22:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PrescriptionOrderInfo extends PrescriptionOrder implements Serializable {

    private String personName;

    private double totalPrice;

    private String userRealName;

    private String department;

    private String secondDepartment;

}
