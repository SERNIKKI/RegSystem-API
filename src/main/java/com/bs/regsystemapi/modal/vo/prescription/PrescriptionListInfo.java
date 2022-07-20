package com.bs.regsystemapi.modal.vo.prescription;

import com.bs.regsystemapi.entity.Prescription;
import com.bs.regsystemapi.entity.PrescriptionOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/5/8 17:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PrescriptionListInfo extends Prescription implements Serializable {

    private String userRealName;

    private String department;

    private String secondDepartment;

    private String personName;

    private String personAddress;

    private String personAge;

    private String personGender;

    private String identifyCard;

    private String personPhone;

    private double reservePrice;

    private List<prescriptionHerbsInfo> prescriptionHerbsList;

    private PrescriptionOrder prescriptionOrder;

}
