package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Prescription;
import com.bs.regsystemapi.modal.dto.prescription.PrescriptionOrderForm;
import com.bs.regsystemapi.modal.dto.prescription.QueryPrescriptionForm;
import com.bs.regsystemapi.modal.vo.patient.PatientListInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionListInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionOrderInfo;
import com.bs.regsystemapi.modal.vo.prescription.prescriptionHerbsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/5/6 11:21
 */
@Mapper
public interface PrescriptionDao extends BaseMapper<Prescription> {
    List<PrescriptionListInfo> getPrescriptionList(QueryPrescriptionForm form);
    List<prescriptionHerbsInfo> getHerbsList(String prescriptionNo);
    PrescriptionListInfo getPrescriptionInfo(String prescriptionNo);
    List<PrescriptionOrderInfo> getOrderInfo(PrescriptionOrderForm form);
}
