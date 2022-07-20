package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Prescription;
import com.bs.regsystemapi.modal.dto.prescription.PrescriptionOrderForm;
import com.bs.regsystemapi.modal.dto.prescription.QueryPrescriptionForm;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionListInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.Map;

/**
 * @author qpj
 * @date 2022/5/6 11:26
 */
public interface PrescriptionService extends IService<Prescription> {
    Map<String, Object> save(PrescriptionInfo prescriptionInfo);
    ManagePageResult getPrescriptionList(QueryPrescriptionForm form);
    void deletePrescription(String prescriptionNo);
    PrescriptionListInfo getPrescriptionInfo(String prescriptionNo);
    ManagePageResult getOrderInfo(PrescriptionOrderForm form);
    void deleteOrder(String orderNo);
}
