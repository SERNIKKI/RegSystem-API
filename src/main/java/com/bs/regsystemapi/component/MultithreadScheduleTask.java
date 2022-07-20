package com.bs.regsystemapi.component;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bs.regsystemapi.entity.ReserveSurplus;
import com.bs.regsystemapi.service.ReserveSurplusService;
import com.bs.regsystemapi.utils.ReserveSurplusUtils;
import com.bs.regsystemapi.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 多线程执行定时任务
 * @author qpj
 * @date 2022/4/27 17:30
 */

@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class MultithreadScheduleTask {

    @Autowired
    private ReserveSurplusService reserveSurplusService;
    // 每日凌晨一点执行
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void clearDoctorReserve() {
        log.info("========开始执行清除上一天预约数操作========");
        // 获取当前日期
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        // 获取前一天日期
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date beforeDate = calendar.getTime();
        String week = TimeUtils.formatDate(beforeDate, "EEEE");
        String dataColum = ReserveSurplusUtils.getDataColum(week);
        UpdateWrapper<ReserveSurplus> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(dataColum + "_morning", 0)
                .set(dataColum + "_afternoon", 0)
                .set(dataColum + "_night", 0);
        boolean update = reserveSurplusService.update(updateWrapper);
        if(update) {
            log.info("========成功========");
        } else {
            log.error("========失败========");
        }
    }
}
