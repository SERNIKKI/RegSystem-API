package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Drug;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugForm;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugListForm;
import com.bs.regsystemapi.modal.vo.drug.DoctorDrugInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 15:19
 */
@Mapper
public interface DrugDao extends BaseMapper<Drug> {
    List<DrugInfo> getDrugList(QueryDrugForm form);
    DrugInfo getDrugInfo(String drugNo);
    int updateDrugInfo(Drug drug);
    void deleteDrug(Drug drug);
    void recoverDrug(Drug drug);
    List<DrugInfo> getDeleteList();
    List<DoctorDrugInfo> getDoctorDrugList(QueryDrugListForm form);
}
