package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugPrice;
import com.bs.regsystemapi.modal.dto.drug.QueryPriceForm;
import com.bs.regsystemapi.modal.vo.drug.NotPriceInfo;
import com.bs.regsystemapi.modal.vo.drug.PriceInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/21 14:41
 */
public interface DrugPriceService extends IService<DrugPrice> {
    List<NotPriceInfo> getNotPriceList();
    ManagePageResult getPriceList(QueryPriceForm form);
    PriceInfo getPriceInfo(String drugNo);
}
