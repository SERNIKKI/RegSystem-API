package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugProdDao;
import com.bs.regsystemapi.entity.DrugProd;
import com.bs.regsystemapi.modal.dto.drug.QueryDrugProdForm;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.service.DrugProdService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/16 11:18
 */
@Service("drugProdService")
public class DrugProdServiceImpl extends ServiceImpl<DrugProdDao, DrugProd> implements DrugProdService {
    @Override
    public List<DrugProd> getDrugProdList(QueryDrugProdForm form) {
        List<DrugProd> drugProdList = this.baseMapper.getDrugProdList(form);
        return drugProdList;
    }

    @Override
    public ManagePageResult getDrugProdList2(QueryDrugProdForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DrugProd> drugProdList = this.baseMapper.getDrugProdList(form);
        PageInfo<DrugProd> drugProdPageInfo = new PageInfo<>(drugProdList);
        return ManagePageResult.getPageResult(drugProdPageInfo);
    }

    @Override
    public DrugProd getDrugProdInfo(String prodNo) {
        DrugProd drugProdInfo = this.baseMapper.getDrugProdInfo(prodNo);
        return drugProdInfo;
    }

    @Override
    public int updateDrugProdInfo(DrugProd drugProd) {
        return this.baseMapper.updateDrugProdInfo(drugProd);
    }

    @Override
    public List<DrugLabelInfo> getDrugProdLabel() {
        List<DrugLabelInfo> drugProdLabel = this.baseMapper.getDrugProdLabel();
        return drugProdLabel;
    }

    @Override
    public void deleteProd(DrugProd drugProd) {
        this.baseMapper.deleteProd(drugProd);
    }

    @Override
    public void recoverProd(DrugProd drugProd) {
        this.baseMapper.recoverProd(drugProd);
    }

    @Override
    public List<DrugProd> getDeleteList() {
        List<DrugProd> deleteList = this.baseMapper.getDeleteList();
        return deleteList;
    }
}
