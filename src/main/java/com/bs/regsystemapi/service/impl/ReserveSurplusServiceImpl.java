package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.ReserveSurplusDao;
import com.bs.regsystemapi.entity.ReserveSurplus;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.exception.ManageException;
import com.bs.regsystemapi.modal.dto.surplus.AddSurplus;
import com.bs.regsystemapi.modal.dto.surplus.AddSurplusDto;
import com.bs.regsystemapi.modal.dto.surplus.QueryDoctorCount;
import com.bs.regsystemapi.modal.vo.surplus.TodayInfo;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.ReserveSurplusService;
import com.bs.regsystemapi.utils.ReserveSurplusUtils;
import com.bs.regsystemapi.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reserveSurplusService")
@Slf4j
public class ReserveSurplusServiceImpl extends ServiceImpl<ReserveSurplusDao, ReserveSurplus> implements ReserveSurplusService {

    @Autowired
    private ReserveSurplusService reserveSurplusService;
    @Autowired
    private LogService logService;

    @Override
    public void saveSurplus(ReserveSurplus reserveSurplus) {
        boolean save = reserveSurplusService.save(reserveSurplus);
        if(save) {
            logService.addLog(Action.INSERT, Table.RESERVE_SURPLUS, reserveSurplus.getSurplusNo());
        } else {
            logService.addLog(Action.INSERT, Table.RESERVE_SURPLUS, reserveSurplus.getSurplusNo(), Status.FAIL);
        }
    }

    @Override
    @Transactional
    public void addSurplus(AddSurplus surplusInfo) {
        log.warn("====================开始查询预约号，week为：{}=======================",surplusInfo.getDate());
        String week = TimeUtils.formatDate(surplusInfo.getDate(), "EEEE");
        log.warn("====================转换后获取到的week为：{}=======================",week);
        String dataColum = ReserveSurplusUtils.getDataColum(week, surplusInfo.getTime());
        AddSurplusDto addSurplusDto = new AddSurplusDto();
        addSurplusDto.setColum(dataColum);
        addSurplusDto.setDoctorNo(surplusInfo.getDoctorNo());
        // 获取预约数
        Integer count = this.baseMapper.getCount(addSurplusDto);
        // 获取总数
        Integer total = this.baseMapper.getTotal(surplusInfo.getDoctorNo());
        if(count != null ) {
            if(count >= 40) {
                throw new ManageException("超出预约数上限!");
            }
            // 更新
            UpdateWrapper<ReserveSurplus> queryWrapper = new UpdateWrapper<>();
            queryWrapper.eq("doctor_no", surplusInfo.getDoctorNo())
                    .set(dataColum, count+1)
                    .set("total", total+1);
            boolean update = reserveSurplusService.update(queryWrapper);
            if(update) {
                logService.addLog(Action.UPDATE, Table.RESERVE_SURPLUS, surplusInfo.getDoctorNo());
            } else {
                logService.addLog(Action.UPDATE, Table.RESERVE_SURPLUS, surplusInfo.getDoctorNo(), Status.FAIL);
            }
        }
    }

    @Override
    public TodayInfo getDoctorCount(QueryDoctorCount queryDoctorCount) {
        String week = TimeUtils.formatDate(queryDoctorCount.getDate(), "EEEE");
        String dataColum = ReserveSurplusUtils.getDataColum(week);
        AddSurplusDto addSurplusDto = new AddSurplusDto(queryDoctorCount.getDoctorNo(), dataColum);
        return this.baseMapper.getTodayInfo(addSurplusDto);
    }

}
