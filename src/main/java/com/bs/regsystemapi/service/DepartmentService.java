package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.modal.dto.department.QueryDepartmentForm;
import com.bs.regsystemapi.modal.vo.FirstDepartmentInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/14 15:35
 */
public interface DepartmentService extends IService<Department> {
    ManagePageResult getDepartmentList(QueryDepartmentForm form);

    List<FirstDepartmentInfo> getFirstDepartList();

    Department getDepartmentInfo(String secondId);

    int updateDepartmentInfo(Department department);

    List<DrugTypeInfo> getTypeInfo();
}
