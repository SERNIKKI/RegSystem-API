package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.PatPayRecord;
import com.bs.regsystemapi.modal.dto.patpayrecord.*;
import com.bs.regsystemapi.utils.ManagePageResult;

/**
 * @Date 2022/4/25 19:39
 */
public interface PatPayRecordService extends IService<PatPayRecord> {
    ManagePageResult getPatPayRecordList(GetPatPayRecordListForm form);

    ManagePageResult getPatPayRecordByDoctorNo(GetPatPayRecordByDoctorNoForm form);

    ManagePageResult getPatPayRecordByOrderNo(GetPatPayRecordByOrderNoForm form);

    ManagePageResult getPatPayRecordByPersonNo(GetPatPayRecordByPersonNoForm form);

    ManagePageResult getPatPayRecordByPatNo(GetPatPayRecordByPatNoForm form);

    ManagePageResult getOrderList(QueryOrderListForm form);

    void deleteOrder(String orderNo);
}
