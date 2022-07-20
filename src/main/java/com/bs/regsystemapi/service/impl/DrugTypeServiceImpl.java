package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugTypeDao;
import com.bs.regsystemapi.entity.DrugType;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.service.DrugTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 23:06
 */
@Service("drugTypeService")
public class DrugTypeServiceImpl extends ServiceImpl<DrugTypeDao, DrugType> implements DrugTypeService {

    @Override
    public List<DrugLabelInfo> getMainTypeList(String type) {
        List<DrugLabelInfo> mainTypeList = this.baseMapper.getMainTypeList(type);
        return mainTypeList;
    }

    @Override
    public List<DrugLabelInfo> getSubTypeList(String mainId) {
        List<DrugLabelInfo> subTypeList = this.baseMapper.getSubTypeList(mainId);
        return subTypeList;
    }

    @Override
    public List<DrugTypeInfo> getDrugTypeList(String type) {
        List<DrugTypeInfo> drugTypeInfos = new ArrayList<>();
        List<DrugLabelInfo> mainTypeList = this.baseMapper.getMainTypeList(type);
        for(DrugLabelInfo labelInfo : mainTypeList) {
            DrugTypeInfo drugTypeInfo = new DrugTypeInfo();
            drugTypeInfo.setLabel(labelInfo.getLabel());
            drugTypeInfo.setValue(labelInfo.getValue());
            List<DrugLabelInfo> subTypeList = this.baseMapper.getSubTypeList(labelInfo.getValue());
            drugTypeInfo.setChildren(subTypeList);
            drugTypeInfos.add(drugTypeInfo);
        }
        return drugTypeInfos;
    }
}
