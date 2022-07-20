package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.entity.TestEntity;
import com.bs.regsystemapi.modal.dto.QueryTestRequestForm;
import com.bs.regsystemapi.modal.dto.UpdateTestForm;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.TestService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author qpj
 * @Date 2022/01/28 15:03
 */
@RestController
@RequestMapping("/test")
@Api(value = "测试用接口")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    @Autowired
    private LogService logService;

    /**
     * 获取列表信息
     */
    @ApiOperation(value = "获取列表信息")
    @RequestMapping(value = "/getTestList", method = RequestMethod.POST)
    public ResponseResult getTestList(@RequestBody QueryTestRequestForm form) {
        ManagePageResult managePageResult = testService.queryTest(form);
        return ResponseResult.ok().put(managePageResult);
    }

    @ApiOperation(value = "保存测试信息")
    @RequestMapping(value = "/saveTest", method = RequestMethod.POST)
    public ResponseResult saveTest(@RequestBody TestEntity testEntity) {
        testService.saveTest(testEntity);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "修改test信息")
    @RequestMapping(value = "/updateTest", method = RequestMethod.POST)
    public ResponseResult updateTest(@RequestBody UpdateTestForm form) {
        if(!StringUtils.isEmpty(form.getTestNo())) {
            testService.updateTest(form);
            return ResponseResult.ok();
        }
        return ResponseResult.error("testNO为空");
    }

    @ApiOperation(value = "删除test")
    @RequestMapping(value = "/deleteTest/{testNo}", method = RequestMethod.GET)
    public ResponseResult deleteTest(@PathVariable String testNo){
        if(!StringUtils.isEmpty(testNo)) {
            testService.deleteTest(testNo);
            return ResponseResult.ok();
        }
        return ResponseResult.error("testNO为空");
    }
}
