package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.ReserveSurplus;
import com.bs.regsystemapi.modal.dto.surplus.AddSurplus;
import com.bs.regsystemapi.modal.dto.surplus.QueryDoctorCount;
import com.bs.regsystemapi.modal.vo.surplus.TodayInfo;

public interface ReserveSurplusService extends IService<ReserveSurplus> {
    void saveSurplus(ReserveSurplus reserveSurplus);
    void addSurplus(AddSurplus addSurplus);
    TodayInfo getDoctorCount(QueryDoctorCount queryDoctorCount);
}
