package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.department.QueryDepartmentForm;
import com.bs.regsystemapi.modal.vo.FirstDepartmentInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.service.DepartmentService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/14 16:06
 */
@RestController
@RequestMapping("/department")
@Api(value = "科室相关接口")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取科室列表")
    @PostMapping(value = "/list")
    public ResponseResult getDepartmentList(@RequestBody QueryDepartmentForm form) {
        ManagePageResult managePageResult = departmentService.getDepartmentList(form);
        return ResponseResult.ok().put(managePageResult);
    }

    @ApiOperation(value = "获取一级科室列表")
    @GetMapping(value = "/firstList")
    public ResponseResult getFirstDepartList() {
        List<FirstDepartmentInfo> firstDepartList = departmentService.getFirstDepartList();
        return ResponseResult.ok().put(firstDepartList);
    }

    @ApiOperation(value = "获取科室详情")
    @GetMapping(value = "/info/{secondId}")
    public ResponseResult getDepartmentInfo(@PathVariable("secondId") String secondId) {
        if(StringUtils.isEmpty(secondId.toString())) {
            return ResponseResult.error("科室id不能为空");
        }
        Department departmentInfo = departmentService.getDepartmentInfo(secondId);
        return ResponseResult.ok().put(departmentInfo);
    }

    @ApiOperation(value = "更改科室信息")
    @PostMapping(value = "/update")
    public ResponseResult updateDepartmentInfo(@RequestBody Department department) {
        if(department.getSecondId().toString().isEmpty()) {
            return ResponseResult.error("科室id不能为空");
        }
        // 获取更改人信息
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("未查找到用户信息");
        }
        UserEntity user =  (UserEntity) StpUtil.getSession().get("user");
        department.setUpdateName(user.getUserRealName());
        department.setUpdateTime(new Date());
        int i = departmentService.updateDepartmentInfo(department);
        if(i > 0) {
            logService.addLog(Action.UPDATE, Table.DEPARTMENT, department.getSecondNo());
        } else {
            logService.addLog(Action.UPDATE, Table.DEPARTMENT, department.getSecondNo(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取一二级科室列表")
    @GetMapping("/label")
    public ResponseResult getDepartmentValue() {
        List<DrugTypeInfo> typeInfo = departmentService.getTypeInfo();
        return ResponseResult.ok().put(typeInfo);
    }
}
