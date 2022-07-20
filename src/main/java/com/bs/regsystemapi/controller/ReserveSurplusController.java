package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.modal.dto.surplus.AddSurplus;
import com.bs.regsystemapi.modal.dto.surplus.QueryDoctorCount;
import com.bs.regsystemapi.modal.vo.surplus.TodayInfo;
import com.bs.regsystemapi.service.ReserveSurplusService;
import com.bs.regsystemapi.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qpj
 * @date 2022/4/20 14:06
 */
@RestController
@RequestMapping("/surplus")
@Api(value = "医生预约数量相关接口")
public class ReserveSurplusController {

    @Autowired
    private ReserveSurplusService reserveSurplusService;

    @ApiOperation(value = "获取指定日期预约数")
    @PostMapping("/countByDoctor")
    public ResponseResult getTodayCount(@RequestBody QueryDoctorCount queryDoctorCount) {
        TodayInfo todayCount = reserveSurplusService.getDoctorCount(queryDoctorCount);
        return ResponseResult.ok().put(todayCount);
    }

    @ApiOperation(value = "新增医生预约数")
    @PostMapping("/addDoctorCount")
    public ResponseResult addSurplus(@RequestBody AddSurplus addSurplus) {
        reserveSurplusService.addSurplus(addSurplus);
        return ResponseResult.ok();
    }

}
