package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PrescriptionDao;
import com.bs.regsystemapi.entity.PatRegRecord;
import com.bs.regsystemapi.entity.Prescription;
import com.bs.regsystemapi.entity.PrescriptionHerbs;
import com.bs.regsystemapi.entity.PrescriptionOrder;
import com.bs.regsystemapi.modal.dto.prescription.PrescriptionOrderForm;
import com.bs.regsystemapi.modal.dto.prescription.QueryPrescriptionForm;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionListInfo;
import com.bs.regsystemapi.modal.vo.prescription.PrescriptionOrderInfo;
import com.bs.regsystemapi.modal.vo.prescription.prescriptionHerbsInfo;
import com.bs.regsystemapi.service.*;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qpj
 * @date 2022/5/6 11:26
 */
@Service("prescriptionService")
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionDao, Prescription> implements PrescriptionService {

    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private PrescriptionHerbsService prescriptionHerbsService;
    @Autowired
    private PrescriptionOrderService prescriptionOrderService;
    @Autowired
    private DrugStockService drugStockService;
    @Autowired
    private PatRegRecordService regRecordService;

    @Override
    @Transactional
    public Map<String, Object> save(PrescriptionInfo prescriptionInfo) {
        // 保存就诊单
        String prescriptionNo = NoGeneratorUtil.getNo();
        prescriptionInfo.setPrescriptionNo(prescriptionNo);
        prescriptionInfo.setCreatTime(new Date());
        Prescription prescription = new Prescription();
        BeanUtils.copyProperties(prescriptionInfo, prescription);
        String herbsNo = NoGeneratorUtil.getNo();
        String orderNo = NoGeneratorUtil.getNo();
        prescription.setDrugsNo(herbsNo);
        prescription.setOrderNo(orderNo);
        prescriptionService.save(prescription);
        // 保存开具的药物
        List<PrescriptionHerbs> prescriptionHerbsList = prescriptionInfo.getPrescriptionHerbs();
        for(PrescriptionHerbs prescriptionHerbs : prescriptionHerbsList) {
            prescriptionHerbs.setPrescriptionNo(prescriptionNo);
            prescriptionHerbs.setHerbsNo(herbsNo);
            boolean save = prescriptionHerbsService.save(prescriptionHerbs);
            // 改变库存
            if(save) {
                drugStockService.reduceDrugStock(prescriptionHerbs.getNum(), prescriptionHerbs.getDrugNo());
            }
        }
        // 生成订单号
        PrescriptionOrder prescriptionOrder = new PrescriptionOrder();
        prescriptionOrder.setOrderNo(orderNo);
        prescriptionOrder.setPrescriptionNo(prescriptionNo);
        prescriptionOrder.setOrderPrice(prescriptionInfo.getTotalPrice());
        prescriptionOrder.setOrderRemark("就诊缴费单");
        prescriptionOrderService.save(prescriptionOrder);
        // 生成就诊单表示预约已完成，未付款则不在考虑范围内
        UpdateWrapper<PatRegRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("reg_no", prescriptionInfo.getRegNo())
                .set("reg_state", "1");
        regRecordService.update(updateWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("prescriptionNo", prescriptionNo);
        return map;
    }

    @Override
    @Transactional
    public ManagePageResult getPrescriptionList(QueryPrescriptionForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<PrescriptionListInfo> prescriptionList = this.baseMapper.getPrescriptionList(form);
        PageInfo<PrescriptionListInfo> prescriptionListInfoPageInfo = new PageInfo<>(prescriptionList);
        List<PrescriptionListInfo> list = prescriptionListInfoPageInfo.getList();
        for(PrescriptionListInfo info : list) {
            List<prescriptionHerbsInfo> herbsList = this.baseMapper.getHerbsList(info.getPrescriptionNo());
            QueryWrapper<PrescriptionOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("prescription_no", info.getPrescriptionNo());
            PrescriptionOrder one = prescriptionOrderService.getOne(queryWrapper);
            info.setPrescriptionOrder(one);
            info.setPrescriptionHerbsList(herbsList);
        }
        return ManagePageResult.getPageResult(prescriptionListInfoPageInfo);
    }

    @Override
    public void deletePrescription(String prescriptionNo) {
        UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("prescription_no", prescriptionNo)
                .set("is_delete", "0");
        prescriptionService.update(updateWrapper);
    }

    @Override
    @Transactional
    public PrescriptionListInfo getPrescriptionInfo(String prescriptionNo) {
        PrescriptionListInfo prescriptionInfo = this.baseMapper.getPrescriptionInfo(prescriptionNo);
        List<prescriptionHerbsInfo> herbsList = this.baseMapper.getHerbsList(prescriptionNo);
        QueryWrapper<PrescriptionOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prescription_no", prescriptionNo);
        PrescriptionOrder one = prescriptionOrderService.getOne(queryWrapper);
        prescriptionInfo.setPrescriptionHerbsList(herbsList);
        prescriptionInfo.setPrescriptionOrder(one);
        return prescriptionInfo;
    }

    @Override
    public ManagePageResult getOrderInfo(PrescriptionOrderForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<PrescriptionOrderInfo> orderInfo = this.baseMapper.getOrderInfo(form);
        PageInfo<PrescriptionOrderInfo> prescriptionOrderInfoPageInfo = new PageInfo<>(orderInfo);
        return ManagePageResult.getPageResult(prescriptionOrderInfoPageInfo);
    }

    @Override
    public void deleteOrder(String orderNo) {
        UpdateWrapper<PrescriptionOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_no", orderNo)
                .set("is_delete", "0");
        prescriptionOrderService.update(updateWrapper);
    }

}
