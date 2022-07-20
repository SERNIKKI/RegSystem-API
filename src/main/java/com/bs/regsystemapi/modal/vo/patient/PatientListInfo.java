package com.bs.regsystemapi.modal.vo.patient;

import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.entity.PatientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/5/7 20:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PatientListInfo extends PatientEntity implements Serializable {

    private List<PatPerson> patPersonList;

}
