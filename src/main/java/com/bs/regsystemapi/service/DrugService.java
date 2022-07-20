package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Drug;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugForm;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugListForm;
import com.bs.regsystemapi.modal.vo.drug.DoctorDrugInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 15:44
 */
public interface DrugService extends IService<Drug> {
    ManagePageResult getDrugList(QueryDrugForm form);
    DrugInfo getDrugInfo(String drugNo);
    int updateDrugInfo(Drug drug);
    void deleteDrug(Drug drug);
    void recoverDrug(Drug drug);
    List<DrugInfo> getDeleteList();
    ManagePageResult getDrugList(QueryDrugListForm form);
}
