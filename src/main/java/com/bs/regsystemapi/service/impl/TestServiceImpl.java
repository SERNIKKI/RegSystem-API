package com.bs.regsystemapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.TestDao;
import com.bs.regsystemapi.entity.TestEntity;
import com.bs.regsystemapi.modal.dto.QueryTestRequestForm;
import com.bs.regsystemapi.modal.dto.UpdateTestForm;
import com.bs.regsystemapi.modal.vo.TestInfo;
import com.bs.regsystemapi.service.TestService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @DATA 2022/01/28
 */
@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestDao, TestEntity> implements TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestService testService;

    @Override
    public ManagePageResult queryTest(QueryTestRequestForm form) {
        //TODO 完成分页操作
        logger.info("开始进行分页操作...");
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<TestInfo> testEntityList = this.baseMapper.selectTestList(form);
        PageInfo<TestInfo> pageInfo = new PageInfo<>(testEntityList);
        return ManagePageResult.getPageResult(pageInfo);
    }

    @Override
    public void saveTest(TestEntity testEntity) {
        // TODO 根据token获取用户信息
        // 生成唯一编号
        String testNo = NoGeneratorUtil.getNo();
        testEntity.setCreateTime(new Date());
        testEntity.setTestNo(testNo);
        testService.save(testEntity);
    }

    @Override
    public void updateTest(UpdateTestForm form) {
        form.setUpdateTime(new Date());
        this.baseMapper.updateTest(form);
    }

    @Override
    public void deleteTest(String testNo) {
        this.baseMapper.deleteTest(testNo);
    }


}
