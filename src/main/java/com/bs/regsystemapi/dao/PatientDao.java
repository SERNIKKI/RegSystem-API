package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.PatientEntity;
import com.bs.regsystemapi.modal.dto.patient.GetPatientListForm;
import com.bs.regsystemapi.modal.dto.patient.PatientRequestForm;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientListInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Date 2022/4/24 21:14
 */
@Mapper
public interface PatientDao extends BaseMapper<PatientEntity> {

    PatientEntity getPatInfoByLogin(PatientRequestForm form);

    PatientEntity selectByOpenId(String openId);

    List<PatientListInfo> getPatientList(GetPatientListForm form);

    PatientInfo getBasePatientInfo(String patNo);
}
