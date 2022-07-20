package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugHerbs;
import com.bs.regsystemapi.modal.dto.drug.QueryDoctorHerbsForm;
import com.bs.regsystemapi.modal.dto.drug.QueryHerbsForm;
import com.bs.regsystemapi.modal.vo.drug.HerbInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:55
 */
public interface DrugHerbsService extends IService<DrugHerbs> {
    ManagePageResult getHerbList(QueryHerbsForm form);
    HerbInfo getHerbInfo(String herbNo);
    int updateHerbInfo(DrugHerbs drugHerbs);
    void deleteHerb(DrugHerbs drugHerbs);
    void recoverHerb(DrugHerbs drugHerbs);
    List<HerbInfo> getDeleteList();
    ManagePageResult getHerbs(QueryDoctorHerbsForm form);
}
