package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.entity.PrescriptionOrder;
import com.bs.regsystemapi.modal.dto.prescription.PrescriptionOrderForm;
import com.bs.regsystemapi.modal.dto.prescription.QueryPrescriptionForm;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionListInfo;
import com.bs.regsystemapi.service.PrescriptionOrderService;
import com.bs.regsystemapi.service.PrescriptionService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qpj
 * @date 2022/5/6 12:13
 */
@RestController
@RequestMapping("/prescription")
@Api(value = "就诊单相关接口")
@Slf4j
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private PrescriptionOrderService prescriptionOrderService;

    @ApiOperation(value = "保存就诊单")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody PrescriptionInfo prescriptionInfo) {
        Map<String, Object> map = prescriptionService.save(prescriptionInfo);
        return ResponseResult.ok().put(map);
    }

    @ApiOperation(value = "完成订单")
    @PostMapping("/overOrder")
    public ResponseResult overOrder(@RequestBody PrescriptionOrder prescriptionOrder) {
        if(StringUtils.isEmpty(prescriptionOrder.getOrderNo())) {
            return ResponseResult.error("订单NO为空!");
        }
        prescriptionOrderService.endOrderStatus(prescriptionOrder);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取就诊单列表")
    @PostMapping("/list")
    public ResponseResult getPrescriptionList(@RequestBody QueryPrescriptionForm form) {
        ManagePageResult prescriptionList = prescriptionService.getPrescriptionList(form);
        return ResponseResult.ok().put(prescriptionList);
    }

    @ApiOperation(value = "删除就诊单")
    @PostMapping("/delete")
    public ResponseResult deletePrescription(@RequestBody String prescriptionNo) {
        if(StringUtils.isEmpty(prescriptionNo)) {
            return ResponseResult.error("就诊单no不存在!");
        }
        prescriptionService.deletePrescription(prescriptionNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取就诊单详情")
    @GetMapping(value = "/info")
    public ResponseResult getPrescriptionInfo(@RequestParam String prescriptionNo) {
        if(StringUtils.isEmpty(prescriptionNo)) {
            return ResponseResult.error("就诊单no不存在!");
        }
        PrescriptionListInfo prescriptionInfo = prescriptionService.getPrescriptionInfo(prescriptionNo);
        return ResponseResult.ok().put(prescriptionInfo);
    }

    @ApiOperation(value = "获取就诊单订单列表")
    @PostMapping("/orderList")
    public ResponseResult getOrderInfo(@RequestBody PrescriptionOrderForm form) {
        ManagePageResult prescriptionList = prescriptionService.getOrderInfo(form);
        return ResponseResult.ok().put(prescriptionList);
    }

    @ApiOperation(value = "删除就诊单订单")
    @PostMapping("/deleteOrder")
    public ResponseResult deleteOrder(@RequestBody String orderNo) {
        if(StringUtils.isEmpty(orderNo)) {
            return ResponseResult.error("就诊单订单no不存在!");
        }
        prescriptionService.deleteOrder(orderNo);
        return ResponseResult.ok();
    }
}
