package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.TestEntity;
import com.bs.regsystemapi.modal.dto.QueryTestRequestForm;
import com.bs.regsystemapi.modal.dto.UpdateTestForm;
import com.bs.regsystemapi.modal.vo.TestInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @DATA 2022/01/28
 */
@Mapper
public interface TestDao extends BaseMapper<TestEntity> {

    List<TestInfo> selectTestList(QueryTestRequestForm form);

    void updateTest(UpdateTestForm form);

    void deleteTest(String testNo);
}
