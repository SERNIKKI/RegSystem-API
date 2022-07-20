package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.PatRegRecord;
import com.bs.regsystemapi.modal.dto.patregrecord.*;
import com.bs.regsystemapi.modal.vo.patpayrecord.PatPayRecordByOtherInfo;
import com.bs.regsystemapi.modal.vo.patpayrecord.RegInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Date 2022/4/25 18:56
 */
@Mapper
public interface PatRegRecordDao extends BaseMapper<PatRegRecord> {

    List<PatRegRecord> getPatRegRecordByDoctorNo(PatRegRecordListForm form);

    List<PatPayRecordByOtherInfo> getPatRegRecordByOther(PatRegRecordRequestForm form);

    List<PatRegRecordDetailInfo> getPatRegRecordByPatNo(GetPatRegRecordByPatNoForm form);

    List<PatRegRecordDetailInfo> getPatRegRecordByRegStateAndPatNo(GetPatRegRecordByOtherAndPatNoForm form);

    List<RegInfo> getRegInfo(QueryRegInfoForm form);
}
