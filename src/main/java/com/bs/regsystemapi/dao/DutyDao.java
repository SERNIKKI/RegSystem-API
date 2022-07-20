package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Duty;
import com.bs.regsystemapi.modal.vo.Duty.DutyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/27 18:03
 */
@Mapper
public interface DutyDao extends BaseMapper<Duty> {
    List<DutyInfo> getDutyList(String userDepartment);
    DutyInfo getDutyByNo(String doctorNo);
}
