package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bs.regsystemapi.entity.PatRegRecord;
import com.bs.regsystemapi.modal.dto.patregrecord.*;
import com.bs.regsystemapi.service.PatRegRecordService;
import com.bs.regsystemapi.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/4/25 18:51
 */
@RestController
@RequestMapping("/patRegRecord")
@Api(value = "挂号记录api接口")
@Slf4j
public class PatRegRecordController {

    @Autowired
    private PatRegRecordService patRegRecordService;

    @ApiOperation(value = "根据医生no查挂号记录")
    @RequestMapping(value = "/getPatRegRecordByDoctorNo", method = RequestMethod.POST)
    public ResponseResult getPatRegRecordByDoctorNo(@RequestBody @Validated PatRegRecordListForm form) throws Exception {
        if (StringUtils.isEmpty(form.getVisitDate())) {
            form.setVisitDate(DateUtil.toString(new Date(), "yyyy-MM-dd"));
        }
        ManagePageResult patRegRecordList = patRegRecordService.getPatRegRecordByDoctorNo(form);
        return ResponseResult.ok().put(patRegRecordList);
    }

    @ApiOperation(value = "根据患者No查挂号记录")
    @RequestMapping(value = "/getPatRegRecordByPatNo", method = RequestMethod.POST)
    public ResponseResult getPatRegRecordByPatNo(@RequestBody GetPatRegRecordByPatNoForm form) {
        String patNo;
        try {
            // 获取patNo
            patNo = (String) StpUtil.getLoginIdByToken((String) ThreadLocalUtils.get("Authorization"));
            form.setPatNo(patNo);
            log.info("用户号:" + patNo);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
        ManagePageResult pageResult = patRegRecordService.getPatRegRecordByPatNo(form);
        return ResponseResult.ok().put(pageResult);
    }

    @ApiOperation(value = "根据医生名字或预约日期或科室查挂号记录")
    @RequestMapping(value = "/getPatRegRecordByOther", method = RequestMethod.POST)
    public ResponseResult getPatRegRecordByOther(@RequestBody PatRegRecordRequestForm form){
        ManagePageResult patRegRecordByOther = patRegRecordService.getPatRegRecordByOther(form);
        return ResponseResult.ok().put(patRegRecordByOther);
    }

    @ApiOperation(value = "根据患者no和预约状态和订单状态获取预约记录")
    @RequestMapping(value = "/getPatRegRecordByOtherAndPatNo", method = RequestMethod.POST)
    public ResponseResult getPatRegRecordByOtherAndPatNo(@RequestBody GetPatRegRecordByOtherAndPatNoForm form) {
        String patNo = "";
        try {
            // 获取patNo
            patNo = (String) StpUtil.getLoginIdByToken((String) ThreadLocalUtils.get("Authorization"));
            form.setPatNo(patNo);
            log.info("用户号:" + patNo);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
        ManagePageResult pageResult = patRegRecordService.getPatRegRecordByOtherAndPatNo(form);
        return ResponseResult.ok().put(pageResult);
    }

    @ApiOperation(value = "预约详情")
    @RequestMapping(value = "/getPatRegRecordDetail", method = RequestMethod.GET)
    public ResponseResult getPatRegRecordDetail(@RequestParam("regNo") String regNo) {
        return ResponseResult.ok().put(patRegRecordService.getPatRegRecordDetail(regNo));
    }

    @ApiOperation(value = "新增挂号", notes = "后端方法待修复")
    @RequestMapping(value = "/savePatRegRecord", method = RequestMethod.POST)
    public ResponseResult savePatRegRecord(@RequestBody @Validated AddPatRegRecordForm form) {
        List<PatRegRecord> patRegRecord = patRegRecordService.getBaseMapper().selectList(
                new QueryWrapper<PatRegRecord>()
                        .eq("doctor_no", form.getDoctorNo())
                        .eq("person_no", form.getPersonNo())
                        .eq("is_delete", "1")
                        .eq("visit_time", form.getVisitTime())
                        .eq("visit_data", form.getVisitData())
        );
        // 查询到该时间段已经已预约该医生
        if (!patRegRecord.isEmpty()) {
            return ResponseResult.error("查询到该时间段已经已预约该医生");
        }
        // 捕捉异常
        try {
            String regNo = patRegRecordService.savePatRegRecord(form);
            return ResponseResult.ok().put("regNo",regNo);
        }catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.toString());
            map.put("data", form);
            return ResponseResult.error().put(map);
        }

    }


    @ApiOperation(value = "取消挂号")
    @RequestMapping(value = "/cancelPatRegRecord", method = RequestMethod.POST)
    public ResponseResult cancelPatRegRecord(@RequestBody @Validated CancelPatRegRecordForm form) {
        UpdateWrapper<PatRegRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("reg_no",form.getRegNo());
        updateWrapper.set("reg_state","2");
        boolean flag = patRegRecordService.update(updateWrapper);
        if (!flag) {
            return ResponseResult.error("取消挂号记录失败!");
        }

        return ResponseResult.ok("取消成功");
    }


    @ApiOperation(value = "删除挂号")
    @RequestMapping(value = "/deletePatRegRecord", method = RequestMethod.POST)
    public ResponseResult deletePatRegRecord(@RequestBody @Validated CancelPatRegRecordForm form) {
        UpdateWrapper<PatRegRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("reg_no",form.getRegNo());
        updateWrapper.set("is_delete","0");
        boolean flag = patRegRecordService.update(updateWrapper);
        if (!flag) {
            return ResponseResult.error("删除挂号记录失败!");
        }
        return ResponseResult.ok("删除成功");
    }


    @ApiOperation(value = "查挂号记录")
    @RequestMapping(value = "/getRegInfo", method = RequestMethod.POST)
    public ResponseResult getPatRegRecordByDoctorNo(@RequestBody QueryRegInfoForm form) {
        ManagePageResult regInfo = patRegRecordService.getRegInfo(form);
        return ResponseResult.ok().put(regInfo);
    }
}
