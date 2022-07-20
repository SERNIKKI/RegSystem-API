package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.entity.Duty;
import com.bs.regsystemapi.entity.ReserveSurplus;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorForm;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorsForm;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.service.*;
import com.bs.regsystemapi.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/25 12:20
 */
@RestController
@RequestMapping("/doctor")
@Api(value = "医生相关接口")
public class DoctorController {

    @Autowired
    private UserService doctorService;
    @Autowired
    private LogService logService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DutyService dutyService;
    @Autowired
    private ReserveSurplusService reserveSurplusService;

    @ApiOperation(value = "获取医生列表")
    @PostMapping(value = "/list")
    public ResponseResult getDoctorList(@RequestBody QueryDoctorForm form) {
        ManagePageResult doctorList = doctorService.getDoctorList(form);
        return ResponseResult.ok().put(doctorList);
    }

    @ApiOperation(value = "获取医生信息")
    @GetMapping(value = "/info/{userNo}")
    public ResponseResult getDoctorInfo(@PathVariable("userNo") String userNo) {
        if(StringUtils.isEmpty(userNo)) {
            return ResponseResult.error("no不能为空");
        }
        DoctorInfo doctorInfo = doctorService.getDoctorInfo(userNo);
        return ResponseResult.ok().put(doctorInfo);
    }

    @ApiOperation(value = "获取医生列表，不分页")
    @PostMapping(value = "/doctors")
    public ResponseResult getDoctorsInfo(@RequestBody QueryDoctorsForm form) {
        List<DoctorInfo> doctorsInfo = doctorService.getDoctorsInfo(form);
        return ResponseResult.ok().put(doctorsInfo);
    }

    @ApiOperation(value = "更改医生信息")
    @PostMapping("/update")
    public ResponseResult updateDoctorInfo(@RequestBody UserEntity doctorInfo) {
        doctorService.updateUser(doctorInfo);
        logService.addLog(Action.UPDATE, Table.USER, doctorInfo.getUserNo());
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除医生")
    @PostMapping(value = "/delete")
    public ResponseResult deleteDoctor(@RequestBody DoctorInfo doctorInfo) {
        if(StringUtils.isEmpty(doctorInfo.getUserNo())) {
            return ResponseResult.error("no不能为空");
        }
        boolean b = doctorService.deleteDoctor(doctorInfo.getUserNo());
        if(b) {
            logService.addLog(Action.DELETE, Table.USER, doctorInfo.getUserNo());
        } else {
            logService.addLog(Action.DELETE, Table.USER, doctorInfo.getUserNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "恢复医生")
    @PostMapping(value = "/recover")
    public ResponseResult recoverDoctor(@RequestBody DoctorInfo doctorInfo) {
        if(StringUtils.isEmpty(doctorInfo.getUserNo())) {
            return ResponseResult.error("no不能为空");
        }
        boolean b = doctorService.recoverDoctor(doctorInfo.getUserNo());
        if(b) {
            logService.addLog(Action.RECOVER, Table.USER, doctorInfo.getUserNo());
        } else {
            logService.addLog(Action.RECOVER, Table.USER, doctorInfo.getUserNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取已删除医生列表")
    @GetMapping(value = "/deleteList")
    public ResponseResult getDeleteList() {
        List<DoctorInfo> deleteList = doctorService.getDeleteList();
        return ResponseResult.ok().put(deleteList);
    }

    @ApiOperation(value = "新增医生")
    @PostMapping(value = "/save")
    @Transactional
    public ResponseResult saveDoctor(@RequestBody UserEntity doctorInfo) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        doctorInfo.setUserAvatar(doctorInfo.getUserWorkAvatar());
        String userName = ChineseCharacterUtil.convertHanzi2Pinyin(doctorInfo.getUserRealName(), true);
        userName = userName + RandomUtils.getrandom(2);
        String password = userName + RandomUtils.getrandom(4);
        doctorInfo.setUserPassword(password);
        doctorInfo.setUserName(userName);
        doctorInfo.setUserNo(NoGeneratorUtil.getNo());
        // 获取部门信息
        Department departmentInfo = departmentService.getDepartmentInfo(doctorInfo.getUserDepartment());
        // 发送邮件
        try {
            boolean send = MailSenderUtil.sendEmail("欢迎加入xx中心医院", doctorInfo.getUserEmail(), doctorInfo, departmentInfo);
            if(send) {
                doctorInfo.setUserPassword(EncodeUtils.encodeHex(password.getBytes()));
                // 新增排班表
                Duty duty = new Duty();
                duty.setDoctorNo(doctorInfo.getUserNo());
                // 新增预约数量表
                ReserveSurplus reserveSurplus = new ReserveSurplus();
                reserveSurplus.setDoctorNo(doctorInfo.getUserNo());
                reserveSurplus.setSurplusNo(NoGeneratorUtil.getNo());
                reserveSurplus.setUpdateName(user.getUserRealName());
                reserveSurplus.setUpdateTime(new Date());
                reserveSurplusService.saveSurplus(reserveSurplus);
                dutyService.saveDuty(duty);
                doctorService.save(doctorInfo);
                logService.addLog(Action.INSERT, Table.USER, doctorInfo.getUserNo());
            } else {
                logService.addLog(Action.INSERT, Table.USER, doctorInfo.getUserNo(), Status.FAIL);
            }
            return ResponseResult.ok();
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseResult.error("保存失败");
        }

    }
}
