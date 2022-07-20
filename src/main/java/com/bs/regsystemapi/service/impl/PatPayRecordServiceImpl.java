package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PatPayRecordDao;
import com.bs.regsystemapi.entity.PatPayRecord;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.entity.PatientEntity;
import com.bs.regsystemapi.modal.dto.patpayrecord.*;
import com.bs.regsystemapi.modal.vo.patpayrecord.OrderListInfo;
import com.bs.regsystemapi.modal.vo.patpayrecord.PatPayRecordByDoctorNoInfo;
import com.bs.regsystemapi.modal.vo.patpayrecord.PatPayRecordInfo;
import com.bs.regsystemapi.service.PatPayRecordService;
import com.bs.regsystemapi.service.PatPersonService;
import com.bs.regsystemapi.service.PatientService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/4/25 19:39
 */
@Service
public class PatPayRecordServiceImpl extends ServiceImpl<PatPayRecordDao, PatPayRecord> implements PatPayRecordService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatPersonService patPersonService;
    @Autowired
    private PatPayRecordService patPayRecordService;

    @Override
    public ManagePageResult getPatPayRecordList(GetPatPayRecordListForm form) {
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        List<PatPayRecord> patPayRecordList = this.baseMapper.getPatPayRecordList(form);
        PageInfo<PatPayRecord> patPayRecordPageInfo = new PageInfo<>(patPayRecordList);
        return ManagePageResult.getPageResult(patPayRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatPayRecordByDoctorNo(GetPatPayRecordByDoctorNoForm form) {
        List<PatPayRecordByDoctorNoInfo> patPayRecords = new ArrayList<>();
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        // 条件查询，字段doctor_no，并且is_delete为1（记录未被删除）
        List<PatPayRecord> patPayRecordList = this.baseMapper.selectList(
                new QueryWrapper<PatPayRecord>()
                        .eq("doctor_no",form.getDoctorNo())
                        .eq("is_delete", "1")
        );
        // 数据处理，获取患者真实姓名，封装到结果集
        for (PatPayRecord payRecord : patPayRecordList) {
            PatPayRecordByDoctorNoInfo info = new PatPayRecordByDoctorNoInfo();
            PatientEntity patient = patientService.getOne(
                    new QueryWrapper<PatientEntity>()
                            .eq("pat_no", payRecord.getPatNo())
                            .select("pat_realName")
            );
            BeanUtils.copyProperties(payRecord, info);
            info.setPatRealName(patient.getPatRealName());
            patPayRecords.add(info);
        }

        PageInfo<PatPayRecordByDoctorNoInfo> patPayRecordPageInfo = new PageInfo<>(patPayRecords);
        return ManagePageResult.getPageResult(patPayRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatPayRecordByOrderNo(GetPatPayRecordByOrderNoForm form) {
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        // 条件查询，字段order_no，并且is_delete为1（记录未被删除）
        List<PatPayRecord> patPayRecords = this.baseMapper.selectList(
                new QueryWrapper<PatPayRecord>().eq("order_no", form.getOrderNo())
                        .eq("is_delete", 1)
        );
        PageInfo<PatPayRecord> patPayRecordPageInfo = new PageInfo<>(patPayRecords);
        return ManagePageResult.getPageResult(patPayRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatPayRecordByPersonNo(GetPatPayRecordByPersonNoForm form) {
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        // 条件查询，字段person_no，并且is_delete为1（记录未被删除）
        List<PatPayRecord> patPayRecords = this.baseMapper.selectList(
                new QueryWrapper<PatPayRecord>()
                        .eq("person_no", form.getPersonNo())
                        .eq("is_delete", "1")
        );
        PageInfo<PatPayRecord> patPayRecordPageInfo = new PageInfo<>(patPayRecords);
        return ManagePageResult.getPageResult(patPayRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatPayRecordByPatNo(GetPatPayRecordByPatNoForm form) {
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }

        QueryWrapper<PatPayRecord> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(form.getOrderState())) {
            queryWrapper.eq("order_state", form.getOrderState());
        }
        // 获取用户下所有的缴费记录  字段pat_no，并且is_delete为1（记录未被删除）
        List<PatPayRecord> patPayRecordList = this.baseMapper.selectList(
                queryWrapper.eq("pat_no", form.getPatNo())
                        .eq("is_delete", "1")
        );
        List<PatPayRecordInfo> resultInfo = new ArrayList<>();
        PatPayRecordInfo info;
        for (PatPayRecord payRecord : patPayRecordList) {
            // 构造缴费记录详情对象
            info = new PatPayRecordInfo();
            PatPerson person = patPersonService.getOne(
                    new QueryWrapper<PatPerson>()
                            .eq("person_no", payRecord.getPersonNo())
            );
            BeanUtils.copyProperties(payRecord, info);
            info.setPersonName(person.getPersonName());
            // 添加进结果集
            resultInfo.add(info);
        }

        PageInfo<PatPayRecordInfo> patPayRecordPageInfo = new PageInfo<>(resultInfo);
        return ManagePageResult.getPageResult(patPayRecordPageInfo);
    }
    /*



     */

    @Override
    public ManagePageResult getOrderList(QueryOrderListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<OrderListInfo> orderList = this.baseMapper.getOrderList(form);
        PageInfo<OrderListInfo> orderListInfoPageInfo = new PageInfo<>(orderList);
        return ManagePageResult.getPageResult(orderListInfoPageInfo);
    }

    @Override
    public void deleteOrder(String orderNo) {
        UpdateWrapper<PatPayRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_no", orderNo)
                .set("is_delete", "0");
        patPayRecordService.update(updateWrapper);
    }
}
