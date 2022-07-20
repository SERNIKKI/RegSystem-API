package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.PrescriptionOrder;

/**
 * @author qpj
 * @date 2022/5/6 11:29
 */
public interface PrescriptionOrderService extends IService<PrescriptionOrder> {
    void endOrderStatus(PrescriptionOrder prescriptionOrder);
}
