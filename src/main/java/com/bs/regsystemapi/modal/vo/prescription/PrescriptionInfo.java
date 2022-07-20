package com.bs.regsystemapi.modal.vo.prescription;

import com.bs.regsystemapi.entity.Prescription;
import com.bs.regsystemapi.entity.PrescriptionHerbs;
import com.bs.regsystemapi.entity.PrescriptionOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/5/6 11:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PrescriptionInfo extends Prescription implements Serializable {

    private List<PrescriptionHerbs> prescriptionHerbs;

    private PrescriptionOrder prescriptionOrder;

}
