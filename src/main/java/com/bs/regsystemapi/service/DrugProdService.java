package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugProd;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugProdForm;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/16 11:17
 */
public interface DrugProdService extends IService<DrugProd> {
    List<DrugProd> getDrugProdList(QueryDrugProdForm form);
    ManagePageResult getDrugProdList2(QueryDrugProdForm form);
    DrugProd getDrugProdInfo(String prodNo);
    int updateDrugProdInfo(DrugProd drugProd);
    List<DrugLabelInfo> getDrugProdLabel();
    void deleteProd(DrugProd drugProd);
    void recoverProd(DrugProd drugProd);
    List<DrugProd> getDeleteList();
}
