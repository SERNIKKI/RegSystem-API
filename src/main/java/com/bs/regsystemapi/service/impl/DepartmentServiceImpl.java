package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DepartmentDao;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.modal.dto.department.QueryDepartmentForm;
import com.bs.regsystemapi.modal.vo.FirstDepartmentInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.service.DepartmentService;
import com.bs.regsystemapi.service.LoginInfoService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/14 15:36
 */
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {

    @Override
    public ManagePageResult getDepartmentList(QueryDepartmentForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Department> departmentList = this.baseMapper.getDepartmentList(form);
        PageInfo<Department> pageInfo = new PageInfo<>(departmentList);
        return ManagePageResult.getPageResult(pageInfo);
    }

    @Override
    public List<FirstDepartmentInfo> getFirstDepartList() {
        return this.baseMapper.getFirstDepartList();
    }

    @Override
    public Department getDepartmentInfo(String secondId) {
        return this.baseMapper.getDepartmentInfo(secondId);
    }

    @Override
    public int updateDepartmentInfo(Department department) {
        return this.baseMapper.updateDepartmentInfo(department);
    }

    @Override
    public List<DrugTypeInfo> getTypeInfo() {
        List<DrugTypeInfo> drugTypeInfos = new ArrayList<>();
        List<DrugLabelInfo> mainValue = this.baseMapper.getMainValue();
        for(DrugLabelInfo labelInfo : mainValue) {
            DrugTypeInfo drugTypeInfo = new DrugTypeInfo();
            drugTypeInfo.setLabel(labelInfo.getLabel());
            drugTypeInfo.setValue(labelInfo.getValue());
            List<DrugLabelInfo> secondValue = this.baseMapper.getSecondValue(labelInfo.getValue());
            drugTypeInfo.setChildren(secondValue);
            drugTypeInfos.add(drugTypeInfo);
        }
        return drugTypeInfos;
    }


}
