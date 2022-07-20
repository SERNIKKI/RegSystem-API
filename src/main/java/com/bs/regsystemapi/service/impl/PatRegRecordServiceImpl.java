package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PatRegRecordDao;
import com.bs.regsystemapi.entity.PatPayRecord;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.entity.PatRegRecord;
import com.bs.regsystemapi.modal.dto.patregrecord.*;
import com.bs.regsystemapi.modal.dto.surplus.AddSurplus;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.modal.vo.patpayrecord.PatPayRecordByOtherInfo;
import com.bs.regsystemapi.modal.vo.patpayrecord.RegInfo;
import com.bs.regsystemapi.service.*;
import com.bs.regsystemapi.utils.BeanCopyUtils;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.RandomUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/4/25 18:55
 */
@Service
public class PatRegRecordServiceImpl extends ServiceImpl<PatRegRecordDao, PatRegRecord> implements PatRegRecordService {

    @Autowired
    private UserService doctorService;
    @Autowired
    private PatPersonService patPersonService;
    @Autowired
    private PatPayRecordService patPayRecordService;
    @Autowired
    private ReserveSurplusService reserveSurplusService;

    @SneakyThrows
    @Override
    public ManagePageResult getPatRegRecordByOther(PatRegRecordRequestForm form) {
        // 是否选择分页
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        List<PatPayRecordByOtherInfo> resultList = this.baseMapper.getPatRegRecordByOther(form);

        PageInfo<PatPayRecordByOtherInfo> patRegRecordPageInfo = new PageInfo<>(resultList);
        return ManagePageResult.getPageResult(patRegRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatRegRecordByOtherAndPatNo(GetPatRegRecordByOtherAndPatNoForm form){
        // 判断是否分页
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        List<PatRegRecordDetailInfo> resultList = this.baseMapper.getPatRegRecordByRegStateAndPatNo(form);
        PageInfo<PatRegRecordDetailInfo> patRegRecordPageInfo = new PageInfo<>(resultList);
        return ManagePageResult.getPageResult(patRegRecordPageInfo);
    }

    @Override
    public Map<String, Object> getPatRegRecordDetail(String regNo) {
        Map<String, Object> result = new HashMap<>(10);
        QueryWrapper<PatRegRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reg_no", regNo);
        // 预约记录
        PatRegRecord patRegRecord = this.baseMapper.selectOne(queryWrapper);
        // 医生信息
        DoctorInfo doctorInfo = doctorService.getDoctorInfo(patRegRecord.getDoctorNo());

        // 医生服务费
        PatPayRecord patPayRecord = patPayRecordService.getBaseMapper().selectOne(
                new QueryWrapper<PatPayRecord>()
                        .eq("reg_no", regNo)
        );
        // 就诊人信息
        PatPerson patPerson = patPersonService.getBaseMapper().selectOne(
                new QueryWrapper<PatPerson>()
                        .eq("person_no", patRegRecord.getPersonNo())
        );

        result.put("patRegRecord", patRegRecord);
        result.put("doctorInfo", doctorInfo);
        result.put("patPayRecord", patPayRecord);
        result.put("patPerson", patPerson);

        return result;
    }

    @Override
    @Transactional
    public String savePatRegRecord(AddPatRegRecordForm form) {
        PatRegRecord patRegRecord = new PatRegRecord();

        // 生成预约号(4位随机数+10位时间戳)
        String time = Long.toString(System.currentTimeMillis());
        time = time.substring(0, time.length() - 3);
        String regNo = RandomUtils.getrandom(4) + time;
        // 预约号
        patRegRecord.setRegNo(regNo);
        // 是否删除
        patRegRecord.setIsDelete("1");
        // 创建时间
        patRegRecord.setCreatTime(new Date());
        // 预约状态: 0失败 1成功
        patRegRecord.setIsSuccess("1");
        // 去空格
        form.setVisitTime(form.getVisitTime().trim());
        BeanCopyUtils.copyProperties(form, patRegRecord);
        // 新增挂号记录
        this.baseMapper.insert(patRegRecord);
        // 新增缴费记录
        PatPerson patPerson = patPersonService.getOne(
                new QueryWrapper<PatPerson>()
                        .eq("person_no", patRegRecord.getPersonNo())
        );
        PatPayRecord patPayRecord = new PatPayRecord();
        patPayRecord.setPatNo(patPerson.getPatNo());
        patPayRecord.setRegNo(regNo);
        patPayRecord.setDoctorNo(patRegRecord.getDoctorNo());
        patPayRecord.setCreatTime(new Date());
        // 生成订单号(8位随机数+10位时间戳)
        String time1 = Long.toString(System.currentTimeMillis());
        time1 = time1.substring(0, time1.length()-3);
        String orderNo = RandomUtils.getrandom(8) + time1;
        patPayRecord.setOrderNo(orderNo);
        DoctorInfo doctorInfo = doctorService.getDoctorInfo(patPayRecord.getDoctorNo());
        patPayRecord.setUserCost(Double.parseDouble(doctorInfo.getReservePrice()));
        patPayRecord.setPersonNo(patPerson.getPersonNo());
        patPayRecordService.save(patPayRecord);
        // 构造参数，更新医生下预约总数量
        AddSurplus addSurplus = new AddSurplus();
        addSurplus.setDoctorNo(patRegRecord.getDoctorNo());
        addSurplus.setDate(patRegRecord.getVisitData());
        addSurplus.setTime(patRegRecord.getVisitTime());
        // 更新医生被预约数量
        reserveSurplusService.addSurplus(addSurplus);

        return patRegRecord.getRegNo();
    }

    @Override
    public ManagePageResult getPatRegRecordByDoctorNo(PatRegRecordListForm form) {
        // 判断是否分页
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        List<PatRegRecord> patRegRecordList = this.baseMapper.getPatRegRecordByDoctorNo(form);
        PageInfo<PatRegRecord> patRegRecordPageInfo = new PageInfo<>(patRegRecordList);
        return ManagePageResult.getPageResult(patRegRecordPageInfo);
    }

    @Override
    public ManagePageResult getPatRegRecordByPatNo(GetPatRegRecordByPatNoForm form) {
        // 判断是否分页
        if (form.getPageNum() != 0 && form.getPageSize() != 0) {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
        }
        List<PatRegRecordDetailInfo> resultList = this.baseMapper.getPatRegRecordByPatNo(form);
        PageInfo<PatRegRecordDetailInfo> patRegRecordPageInfo = new PageInfo<>(resultList);
        return ManagePageResult.getPageResult(patRegRecordPageInfo);

    }

    @Override
    public ManagePageResult getRegInfo(QueryRegInfoForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<RegInfo> regInfo = this.baseMapper.getRegInfo(form);
        PageInfo<RegInfo> regInfoPageInfo = new PageInfo<>(regInfo);
        return ManagePageResult.getPageResult(regInfoPageInfo);
    }
}
