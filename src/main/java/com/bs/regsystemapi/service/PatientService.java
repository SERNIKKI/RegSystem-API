package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.PatientEntity;
import com.bs.regsystemapi.modal.dto.patient.GetPatientListForm;
import com.bs.regsystemapi.modal.dto.patient.PatientRequestForm;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

/**
 *
 * @date 2022-4-24 21:08:06
 */
public interface PatientService extends IService<PatientEntity> {
    PatientEntity getPatInfoByLogin(PatientRequestForm form);

    PatientEntity selectByOpenId(String openId);

    ManagePageResult getPatientList(GetPatientListForm form);

    PatientInfo getBasePatientInfo(String patNo);
}
