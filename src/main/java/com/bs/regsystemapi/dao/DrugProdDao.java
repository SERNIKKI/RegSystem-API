package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugProd;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugProdForm;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/16 10:59
 */
@Mapper
public interface DrugProdDao extends BaseMapper<DrugProd> {
    List<DrugProd> getDrugProdList(QueryDrugProdForm form);
    DrugProd getDrugProdInfo(String prodNo);
    int updateDrugProdInfo(DrugProd drugProd);
    List<DrugLabelInfo> getDrugProdLabel();
    void deleteProd(DrugProd drugProd);
    void recoverProd(DrugProd drugProd);
    List<DrugProd> getDeleteList();
}
