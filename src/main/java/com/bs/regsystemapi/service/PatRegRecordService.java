package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.PatRegRecord;
import com.bs.regsystemapi.modal.dto.patregrecord.*;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.Map;

/**
 * @Date 2022/4/25 18:54
 */
public interface PatRegRecordService extends IService<PatRegRecord> {
    ManagePageResult getPatRegRecordByOther(PatRegRecordRequestForm form);


    Map<String, Object> getPatRegRecordDetail(String regNo);


    String savePatRegRecord(AddPatRegRecordForm form);

    ManagePageResult getPatRegRecordByDoctorNo(PatRegRecordListForm form);

    ManagePageResult getPatRegRecordByPatNo(GetPatRegRecordByPatNoForm form);

    ManagePageResult getPatRegRecordByOtherAndPatNo(GetPatRegRecordByOtherAndPatNoForm form);

    ManagePageResult getRegInfo(QueryRegInfoForm form);
}
