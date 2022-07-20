package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.service.DrugTypeService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/15 23:08
 */
@RestController
@RequestMapping("/drugType")
@Api(value = "药品作用类别接口")
public class DrugTypeController {

    @Autowired
    private DrugTypeService drugTypeService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取作用类别一级分类列表")
    @GetMapping("/mainList/{type}")
    public ResponseResult getMainTypeList(@PathVariable("type") String type) {
        List<DrugLabelInfo> mainTypeList = drugTypeService.getMainTypeList(type);
        return ResponseResult.ok().put(mainTypeList);
    }

    @ApiOperation(value = "获取作用类别二级分类列表")
    @GetMapping(value = "/subList/{mainId}")
    public ResponseResult getSubTypeList(@PathVariable("mainId") String mainId) {
        List<DrugLabelInfo> subTypeList = drugTypeService.getSubTypeList(mainId);
        return ResponseResult.ok().put(subTypeList);
    }

    @ApiOperation(value = "获取西药作用类别列表")
    @GetMapping(value = "/list/{type}")
    public ResponseResult getDrugTypeList(@PathVariable("type") String type) {
        List<DrugTypeInfo> drugTypeList = drugTypeService.getDrugTypeList(type);
        return ResponseResult.ok().put(drugTypeList);
    }
}
