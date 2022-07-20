package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Duty;
import com.bs.regsystemapi.modal.vo.Duty.DoctorDuty;
import com.bs.regsystemapi.modal.vo.Duty.DutyInfo;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/27 18:12
 */
public interface DutyService extends IService<Duty> {

    void saveDuty(Duty duty);

    List<DutyInfo> getDutyList(String userDepartment);

    DoctorDuty getDutyByNo(String doctorNo);
}
