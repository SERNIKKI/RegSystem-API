package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugHerbsDao;
import com.bs.regsystemapi.entity.DrugHerbs;
import com.bs.regsystemapi.modal.dto.drug.QueryDoctorHerbsForm;
import com.bs.regsystemapi.modal.dto.drug.QueryHerbsForm;
import com.bs.regsystemapi.modal.vo.drug.DoctorHerbInfo;
import com.bs.regsystemapi.modal.vo.drug.HerbInfo;
import com.bs.regsystemapi.service.DrugHerbsService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:56
 */
@Service("drugHerbsService")
public class DrugHerbsServiceImpl extends ServiceImpl<DrugHerbsDao, DrugHerbs> implements DrugHerbsService {

    @Override
    public ManagePageResult getHerbList(QueryHerbsForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<HerbInfo> herbList = this.baseMapper.getHerbList(form);
        PageInfo<HerbInfo> herbInfoPageInfo = new PageInfo<>(herbList);
        return ManagePageResult.getPageResult(herbInfoPageInfo);
    }

    @Override
    public HerbInfo getHerbInfo(String herbNo) {
        HerbInfo herbInfo = this.baseMapper.getHerbInfo(herbNo);
        return herbInfo;
    }

    @Override
    public int updateHerbInfo(DrugHerbs drugHerbs) {
        int i = this.baseMapper.updateHerbInfo(drugHerbs);
        return i;
    }

    @Override
    public void deleteHerb(DrugHerbs drugHerbs) {
        this.baseMapper.deleteHerb(drugHerbs);
    }

    @Override
    public void recoverHerb(DrugHerbs drugHerbs) {
        this.baseMapper.recoverHerb(drugHerbs);
    }

    @Override
    public List<HerbInfo> getDeleteList() {
        List<HerbInfo> deleteList = this.baseMapper.getDeleteList();
        return deleteList;
    }

    @Override
    public ManagePageResult getHerbs(QueryDoctorHerbsForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DoctorHerbInfo> herbs = this.baseMapper.getHerbs(form);
        PageInfo<DoctorHerbInfo> doctorHerbInfoPageInfo = new PageInfo<>(herbs);
        return ManagePageResult.getPageResult(doctorHerbInfoPageInfo);
    }
}
