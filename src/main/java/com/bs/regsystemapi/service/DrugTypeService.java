package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugType;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 23:05
 */
public interface DrugTypeService extends IService<DrugType> {
    List<DrugLabelInfo> getMainTypeList(String type);
    List<DrugLabelInfo> getSubTypeList(String mainId);
    List<DrugTypeInfo> getDrugTypeList(String type);
}
