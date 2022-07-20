package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugPriceDao;
import com.bs.regsystemapi.entity.DrugPrice;
import com.bs.regsystemapi.modal.dto.drug.QueryPriceForm;
import com.bs.regsystemapi.modal.vo.drug.NotPriceInfo;
import com.bs.regsystemapi.modal.vo.drug.PriceInfo;
import com.bs.regsystemapi.service.DrugPriceService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qpj
 * @date 2022/3/21 14:42
 */
@Service("drugPriceService")
public class DrugPriceServiceImpl extends ServiceImpl<DrugPriceDao, DrugPrice> implements DrugPriceService {

    @Override
    public List<NotPriceInfo> getNotPriceList() {
        List<NotPriceInfo> notPriceList = this.baseMapper.getNotPriceList();
        return notPriceList;
    }

    @Override
    public ManagePageResult getPriceList(QueryPriceForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<PriceInfo> priceList = this.baseMapper.getPriceList(form);
        PageInfo<PriceInfo> priceInfoPageInfo = new PageInfo<>(priceList);
        return ManagePageResult.getPageResult(priceInfoPageInfo);
    }

    @Override
    public PriceInfo getPriceInfo(String drugNo) {
        PriceInfo priceInfo = this.baseMapper.getPriceInfo(drugNo);
        return priceInfo;
    }
}
