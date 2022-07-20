package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.modal.dto.department.QueryDepartmentForm;
import com.bs.regsystemapi.modal.vo.FirstDepartmentInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/14 15:34
 */
@Mapper
public interface DepartmentDao extends BaseMapper<Department> {
    List<Department> getDepartmentList(QueryDepartmentForm form);
    List<FirstDepartmentInfo> getFirstDepartList();
    Department getDepartmentInfo(String secondId);
    int updateDepartmentInfo(Department department);
    List<DrugLabelInfo> getMainValue();
    List<DrugLabelInfo> getSecondValue(String secondId);
}
