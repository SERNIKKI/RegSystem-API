package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugType;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 22:35
 */
@Mapper
public interface DrugTypeDao extends BaseMapper<DrugType> {
    List<DrugLabelInfo> getMainTypeList(String type);
    List<DrugLabelInfo> getSubTypeList(String mainId);
}
