package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.regsystemapi.entity.PatPayRecord;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.modal.dto.patpayrecord.*;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.service.PatPayRecordService;
import com.bs.regsystemapi.service.PatPersonService;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Date 2022/4/25 19:01
 */
@RestController
@RequestMapping("/patPayRecord")
@Api(value = "缴费记录api接口")
@Slf4j
public class PatPayRecordController {

    @Autowired
    private PatPayRecordService patPayRecordService;
    @Autowired
    private UserService doctorService;
    @Autowired
    private PatPersonService patPersonService;
    @ApiOperation(value = "获取缴费记录")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult getPatPayRecordList(@RequestBody GetPatPayRecordListForm form) {
        ManagePageResult patPayRecord = patPayRecordService.getPatPayRecordList(form);
        return ResponseResult.ok().put(patPayRecord);
    }

    @ApiOperation(value = "通过就诊卡号获取缴费记录")
    @RequestMapping(value = "/getPatPayRecordByPersonNo", method = RequestMethod.POST)
    public ResponseResult getPatPayRecordByPersonNo(@RequestBody @Validated GetPatPayRecordByPersonNoForm form) {
        ManagePageResult pageResult = patPayRecordService.getPatPayRecordByPersonNo(form);
        return ResponseResult.ok().put(pageResult);
    }

    @ApiOperation(value = "获取当前用户下所有状态的缴费记录")
    @RequestMapping(value = "/getPatPayRecordByPatNo", method = RequestMethod.POST)
    public ResponseResult getPatPayRecordByPatNo(@RequestBody GetPatPayRecordByPatNoForm form) {
        String patNo;
        try {
            patNo = StpUtil.getLoginIdByToken(ThreadLocalUtils.get("Authorization").toString()).toString();
            log.info("用户号:" + patNo);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
        ManagePageResult pageResult = patPayRecordService.getPatPayRecordByPatNo(form);
        return ResponseResult.ok().put(pageResult);

    }

    @ApiOperation(value = "通过医生no获取缴费记录")
    @RequestMapping(value = "/getPatPayRecordByDoctorNo", method = RequestMethod.POST)
    public ResponseResult getPatPayRecordByDoctorNo(@RequestBody @Validated GetPatPayRecordByDoctorNoForm form) {
        ManagePageResult patPayRecordByDoctorNo = patPayRecordService.getPatPayRecordByDoctorNo(form);
        return ResponseResult.ok().put(patPayRecordByDoctorNo);
    }

    @ApiOperation(value = "通过订单号获取缴费记录")
    @RequestMapping(value = "/getPatPayRecordByOrderNo", method = RequestMethod.POST)
    public ResponseResult getPatPayRecordByOrderNo(@RequestBody @Validated GetPatPayRecordByOrderNoForm form) {
        ManagePageResult pageResult = patPayRecordService.getPatPayRecordByOrderNo(form);
        return ResponseResult.ok().put(pageResult);
    }

    @ApiOperation(value = "完成缴费")
    @RequestMapping(value = "/updatePatPayRecord", method = RequestMethod.POST)
    public ResponseResult updatePatPayRecord(@RequestBody @Validated FinishPay finishPay) {
        Date payTime = new Date();
        // 通过订单号获取指定订单
        QueryWrapper<PatPayRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",finishPay.getOrderNo());
        PatPayRecord payRecord = patPayRecordService.getBaseMapper().selectOne(queryWrapper);
        if (payRecord != null) {
            // 缴费时间
            payRecord.setPayTime(payTime);
            // 订单状态
            payRecord.setOrderState("1");
            // 付款类型
            payRecord.setPayType(finishPay.getPayType());
            // 支付方式
            payRecord.setPayMode(finishPay.getPayMode());
            // 订单完成时间
            payRecord.setFinishTime(new Date());
            // 就诊卡号
            payRecord.setPersonNo(finishPay.getPersonNo());
            patPayRecordService.getBaseMapper().updateById(payRecord);
            return ResponseResult.ok("缴费成功");
        }
        return ResponseResult.error("订单号不存在，请传入正确的订单号!");
    }


    @ApiOperation(value = "新增缴费")
    @RequestMapping(value = "/savePatPayRecord", method = RequestMethod.POST)
    public ResponseResult savePatPayRecord(@RequestBody @Validated AddPayRecord patPayRecord) {
        PatPerson patPerson = patPersonService.getOne(
                new QueryWrapper<PatPerson>()
                        .eq("person_no", patPayRecord.getPersonNo())
        );
        if (Objects.isNull(patPerson)) {
            return ResponseResult.error("就诊卡号不存在！");
        }

        PatPayRecord payRecord = new PatPayRecord();
        // 生成订单号(8位随机数+10位时间戳)
        String time = Long.toString(System.currentTimeMillis());
        time = time.substring(0, time.length()-3);
        String orderNo = RandomUtils.getrandom(8) + time;
        payRecord.setOrderNo(orderNo);
        // 订单创建时间
        payRecord.setCreatTime(new Date());
        // 是否删除：1 正常
        payRecord.setIsDelete("1");
        // 用户No
        payRecord.setPatNo(patPerson.getPatNo());
        // 预约号
        payRecord.setRegNo(patPayRecord.getRegNo());
        // 医生No
        payRecord.setDoctorNo(patPayRecord.getDoctorNo());
        // 就诊卡号
        payRecord.setPersonNo(patPerson.getPersonNo());

        DoctorInfo doctorInfo = doctorService.getDoctorInfo(patPayRecord.getDoctorNo());
        // 挂号费用
        payRecord.setUserCost(Double.parseDouble(doctorInfo.getReservePrice()));

        patPayRecordService.save(payRecord);
        return ResponseResult.ok();
    }



    @ApiOperation(value = "缴费详情")
    @RequestMapping(value = "/patPayRecordDetail", method = RequestMethod.POST)
    public ResponseResult patPayRecordDetail(@RequestBody @Validated PatPayRecordDetail detail) {
        // 通过订单编号查询订单记录
        QueryWrapper<PatPayRecord> queryWrapper = new QueryWrapper<>();
        // 条件查询，字段order_no，并且is_delete为1（记录未被删除）
        queryWrapper.eq("order_no", detail.getOrderNo());
        queryWrapper.eq("is_delete", "1");
        // 缴费记录部分详情
        PatPayRecord patPayRecord = patPayRecordService.getOne(queryWrapper);
        // 医生详情部分
        DoctorInfo doctorInfo = doctorService.getDoctorInfo(detail.getDoctorNo());
        Map<String, Object> result = new HashMap<>(10);
        result.put("patPayRecord", patPayRecord);
        result.put("doctorInfo", doctorInfo);
        return ResponseResult.ok().put(result);
    }

    @ApiOperation(value = "获取预约订单列表")
    @PostMapping(value = "/orderList")
    public ResponseResult getOrderList(@RequestBody QueryOrderListForm form) {
        ManagePageResult orderList = patPayRecordService.getOrderList(form);
        return ResponseResult.ok().put(orderList);
    }

    @ApiOperation(value = "删除订单")
    @PostMapping(value = "/delete")
    public ResponseResult deleteOrder(@RequestBody String orderNo) {
        if(StringUtils.isEmpty(orderNo)) {
            return ResponseResult.error("订单no不能为空");
        }
        patPayRecordService.deleteOrder(orderNo);
        return ResponseResult.ok();
    }
}
