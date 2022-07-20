package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DutyDao;
import com.bs.regsystemapi.entity.Duty;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.vo.Duty.DoctorDuty;
import com.bs.regsystemapi.modal.vo.Duty.DutyInfo;
import com.bs.regsystemapi.modal.vo.Duty.DutyList;
import com.bs.regsystemapi.service.DutyService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/27 18:12
 */
@Service("dutyService")
public class DutyServiceImpl extends ServiceImpl<DutyDao, Duty> implements DutyService {

    @Autowired
    private DutyService dutyService;
    @Autowired
    private LogService logService;

    @Override
    public void saveDuty(Duty duty) {
        duty.setDutyNo(NoGeneratorUtil.getNo());
        boolean save = dutyService.save(duty);
        if(save) {
            logService.addLog(Action.INSERT, Table.DUTY, duty.getDutyNo());
        } else {
            logService.addLog(Action.INSERT, Table.DUTY, duty.getDutyNo(), Status.FAIL);
        }
    }

    @Override
    public List<DutyInfo> getDutyList(String userDepartment) {
        List<DutyInfo> dutyList = this.baseMapper.getDutyList(userDepartment);
        return dutyList;
    }

    @Override
    public DoctorDuty getDutyByNo(String doctorNo) {
        DutyInfo info = this.baseMapper.getDutyByNo(doctorNo);
        DoctorDuty doctorDuty = new DoctorDuty(info.getMorningBegin(),info.getMorningEnd(),info.getAfternoonBegin(),
                info.getAfternoonEnd(), info.getNightBegin(), info.getNightEnd(), info.getUserRealName());
        String[] times = {"早上", "下午", "晚上"};
        List<DutyList> dutyLists = new ArrayList<>();
        for(String time : times) {
            DutyList dutyList = new DutyList();
            dutyList.setTime(time);
            if(time.equals("早上")) {
                dutyList.setMon(info.getMonMorning());
                dutyList.setThur(info.getThurMorning());
                dutyList.setTue(info.getTueMorning());
                dutyList.setWed(info.getWedMorning());
                dutyList.setFir(info.getFriMorning());
                dutyList.setSat(info.getSatMorning());
                dutyList.setSun(info.getSunMorning());
            } else if(time.equals("下午")) {
                dutyList.setMon(info.getMonAfternoon());
                dutyList.setThur(info.getThurAfternoon());
                dutyList.setTue(info.getTueAfternoon());
                dutyList.setWed(info.getWedAfternoon());
                dutyList.setFir(info.getFirAfternoon());
                dutyList.setSat(info.getSatAfternoon());
                dutyList.setSun(info.getSunAfternoon());
            } else {
                dutyList.setMon(info.getMonNight());
                dutyList.setThur(info.getThurNight());
                dutyList.setTue(info.getTueNight());
                dutyList.setWed(info.getWedNight());
                dutyList.setFir(info.getFirNight());
                dutyList.setSat(info.getSatNight());
                dutyList.setSun(info.getSunNight());
            }
            dutyLists.add(dutyList);
        }
        doctorDuty.setDutyLists(dutyLists);
        return doctorDuty;
    }
}
