package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.TestEntity;
import com.bs.regsystemapi.modal.dto.QueryTestRequestForm;
import com.bs.regsystemapi.modal.dto.UpdateTestForm;
import com.bs.regsystemapi.utils.ManagePageResult;

/**
 * @author qpj
 * @DATA 2022/01/28
 */
public interface TestService extends IService<TestEntity> {
    ManagePageResult queryTest(QueryTestRequestForm form);

    void saveTest(TestEntity testEntity);

    void updateTest(UpdateTestForm form);

    void deleteTest(String testNo);
}
