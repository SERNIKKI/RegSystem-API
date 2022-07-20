package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugPrice;
import com.bs.regsystemapi.modal.dto.drug.QueryPriceForm;
import com.bs.regsystemapi.modal.vo.drug.NotPriceInfo;
import com.bs.regsystemapi.modal.vo.drug.PriceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/21 14:38
 */
@Mapper
public interface DrugPriceDao extends BaseMapper<DrugPrice> {
    List<NotPriceInfo> getNotPriceList();
    List<PriceInfo> getPriceList(QueryPriceForm form);
    PriceInfo getPriceInfo(String drugNo);
}
