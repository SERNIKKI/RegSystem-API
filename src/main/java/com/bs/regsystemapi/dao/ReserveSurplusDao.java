package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.ReserveSurplus;
import com.bs.regsystemapi.modal.dto.surplus.AddSurplusDto;
import com.bs.regsystemapi.modal.vo.surplus.SurplusInfo;
import com.bs.regsystemapi.modal.vo.surplus.TodayInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReserveSurplusDao extends BaseMapper<ReserveSurplus> {
    Integer getCount(AddSurplusDto addSurplusDto);
    List<SurplusInfo> getSurplusList(String userDepartment);
    SurplusInfo getSurplusByNo(String doctorNo);
    TodayInfo getTodayInfo(AddSurplusDto addSurplusDto);
    Integer getTotal(String doctorNo);
}
