package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugHerbs;
import com.bs.regsystemapi.modal.dto.drug.QueryDoctorHerbsForm;
import com.bs.regsystemapi.modal.dto.drug.QueryHerbsForm;
import com.bs.regsystemapi.modal.vo.drug.DoctorHerbInfo;
import com.bs.regsystemapi.modal.vo.drug.HerbInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:50
 */
@Mapper
public interface DrugHerbsDao extends BaseMapper<DrugHerbs> {
    List<HerbInfo> getHerbList(QueryHerbsForm form);
    HerbInfo getHerbInfo(String herbNo);
    int updateHerbInfo(DrugHerbs drugHerbs);
    void deleteHerb(DrugHerbs drugHerbs);
    void recoverHerb(DrugHerbs drugHerbs);
    List<HerbInfo> getDeleteList();
    List<DoctorHerbInfo> getHerbs(QueryDoctorHerbsForm form);
}
