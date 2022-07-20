package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Duty;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.vo.Duty.DoctorDuty;
import com.bs.regsystemapi.modal.vo.Duty.DutyInfo;
import com.bs.regsystemapi.service.DutyService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/27 18:13
 */
@RestController
@RequestMapping("/duty")
@Api(value = "值班表相关接口")
public class DutyController {

    @Autowired
    private LogService logService;
    @Autowired
    private DutyService dutyService;

    @ApiOperation(value = "批量排班")
    @PostMapping("/batchUpdate")
    public ResponseResult saveDutyInfo(@RequestBody List<Duty> dutyList) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("请先登录");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        List<String> nos = new ArrayList<>();
        for(Duty duty : dutyList) {
            nos.add(duty.getDutyNo());
            duty.setUpdateName(user.getUserRealName());
            duty.setUpdateTime(new Date());
        }
        boolean b = dutyService.updateBatchById(dutyList);
        if(b) {
            logService.addLog(Action.BATCH_UPDATE, Table.DUTY, nos.toString());
        } else {
            logService.addLog(Action.BATCH_UPDATE, Table.DUTY, nos.toString(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "排班")
    @PostMapping("/update")
    public ResponseResult saveDutyInfoByNo(@RequestBody Duty duty) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("请先登录");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        duty.setUpdateName(user.getUserRealName());
        duty.setUpdateTime(new Date());
        boolean b = dutyService.updateById(duty);
        if(b) {
            logService.addLog(Action.UPDATE, Table.DUTY, duty.getDutyNo());
        } else {
            logService.addLog(Action.UPDATE, Table.DUTY, duty.getDutyNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据科室获取排班列表")
    @GetMapping("/listByDep/{userDepartment}")
    public ResponseResult getDutyList(@PathVariable("userDepartment") String userDepartment) {
        if(StringUtils.isEmpty(userDepartment)) {
            return ResponseResult.error("请填入科室no");
        }
        List<DutyInfo> dutyList = dutyService.getDutyList(userDepartment);
        return ResponseResult.ok().put(dutyList);
    }

    @ApiOperation(value = "根据医生no获取排班表")
    @GetMapping("/info/{doctorNo}")
    public ResponseResult getDutyByNo(@PathVariable("doctorNo") String doctorNo) {
        if(StringUtils.isEmpty(doctorNo)) {
            return ResponseResult.error("请填入医生no");
        }
        DoctorDuty dutyByNo = dutyService.getDutyByNo(doctorNo);
        return ResponseResult.ok().put(dutyByNo);
    }
}
