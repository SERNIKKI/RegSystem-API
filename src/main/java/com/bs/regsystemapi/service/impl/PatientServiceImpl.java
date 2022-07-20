package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PatientDao;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.entity.PatientEntity;
import com.bs.regsystemapi.modal.dto.patient.GetPatientListForm;
import com.bs.regsystemapi.modal.dto.patient.PatientRequestForm;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientListInfo;
import com.bs.regsystemapi.service.PatPersonService;
import com.bs.regsystemapi.service.PatientService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientDao, PatientEntity> implements PatientService {

    @Autowired
    private PatPersonService patPersonService;

    @Override
    public PatientEntity getPatInfoByLogin(PatientRequestForm form) {
        return this.baseMapper.getPatInfoByLogin(form);
    }

    @Override
    public PatientEntity selectByOpenId(String openId) {
        return this.baseMapper.selectByOpenId(openId);
    }

    @Override
    public ManagePageResult getPatientList(GetPatientListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<PatientListInfo> patientList = this.baseMapper.getPatientList(form);
        PageInfo<PatientListInfo> patientListInfoPageInfo = new PageInfo<>(patientList);
        List<PatientListInfo> list = patientListInfoPageInfo.getList();
        for(PatientListInfo patientListInfo : list) {
            QueryWrapper<PatPerson> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pat_no", patientListInfo.getPatNo())
                    .eq("is_delete", "1");
            List<PatPerson> patPersonList = patPersonService.list(queryWrapper);
            patientListInfo.setPatPersonList(patPersonList);
        }
        return ManagePageResult.getPageResult(patientListInfoPageInfo);
    }

    @Override
    public PatientInfo getBasePatientInfo(String patNo) {
        return this.baseMapper.getBasePatientInfo(patNo);
    }
}
