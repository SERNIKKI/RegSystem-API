package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PrescriptionOrderDao;
import com.bs.regsystemapi.entity.PrescriptionOrder;
import com.bs.regsystemapi.service.PrescriptionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author qpj
 * @date 2022/5/6 11:29
 */
@Service("prescriptionOrderService")
public class PrescriptionOrderServiceImpl extends ServiceImpl<PrescriptionOrderDao, PrescriptionOrder> implements PrescriptionOrderService {

    @Autowired
    private PrescriptionOrderService prescriptionOrderService;

    @Override
    @Transactional
    public void endOrderStatus(PrescriptionOrder prescriptionOrder) {
        // 完成就诊单
        UpdateWrapper<PrescriptionOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_no", prescriptionOrder.getOrderNo())
                .set("order_status", prescriptionOrder.getOrderStatus())
                .set("pay_way", prescriptionOrder.getPayWay())
                .set("pay_time", new Date());
        prescriptionOrderService.update(updateWrapper);
    }
}
