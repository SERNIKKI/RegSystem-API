package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugDao;
import com.bs.regsystemapi.entity.Drug;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugForm;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugListForm;
import com.bs.regsystemapi.modal.vo.drug.DoctorDrugInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugInfo;
import com.bs.regsystemapi.service.DrugService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 15:45
 */
@Service("drugService")
public class DrugServiceImpl extends ServiceImpl<DrugDao, Drug> implements DrugService {
    @Override
    public ManagePageResult getDrugList(QueryDrugForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DrugInfo> drugList = this.baseMapper.getDrugList(form);
        PageInfo<DrugInfo> drugPageInfo = new PageInfo<>(drugList);
        return ManagePageResult.getPageResult(drugPageInfo);
    }

    @Override
    public DrugInfo getDrugInfo(String drugNo) {
        DrugInfo drugInfo = this.baseMapper.getDrugInfo(drugNo);
        return drugInfo;
    }

    @Override
    public int updateDrugInfo(Drug drug) {
        return this.baseMapper.updateDrugInfo(drug);
    }

    @Override
    public void deleteDrug(Drug drug) {
        this.baseMapper.deleteDrug(drug);
    }

    @Override
    public void recoverDrug(Drug drug) {
        this.baseMapper.recoverDrug(drug);
    }

    @Override
    public List<DrugInfo> getDeleteList() {
        List<DrugInfo> deleteList = this.baseMapper.getDeleteList();
        return deleteList;
    }

    @Override
    public ManagePageResult getDrugList(QueryDrugListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DoctorDrugInfo> drugList = this.baseMapper.getDoctorDrugList(form);
        PageInfo<DoctorDrugInfo> doctorDrugInfoPageInfo = new PageInfo<>(drugList);
        return ManagePageResult.getPageResult(doctorDrugInfoPageInfo);
    }
}
