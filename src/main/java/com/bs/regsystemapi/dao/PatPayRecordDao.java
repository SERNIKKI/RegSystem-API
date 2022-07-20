package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.PatPayRecord;
import com.bs.regsystemapi.modal.dto.patpayrecord.GetPatPayRecordListForm;
import com.bs.regsystemapi.modal.dto.patpayrecord.QueryOrderListForm;
import com.bs.regsystemapi.modal.vo.patpayrecord.OrderListInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Date 2022/4/25 19:40
 */
@Mapper
public interface PatPayRecordDao extends BaseMapper<PatPayRecord> {
    List<PatPayRecord> getPatPayRecordList(GetPatPayRecordListForm form);

    List<OrderListInfo> getOrderList(QueryOrderListForm form);
}
