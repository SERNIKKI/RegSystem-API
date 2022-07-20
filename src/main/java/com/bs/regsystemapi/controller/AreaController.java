package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.modal.vo.province.ParentProvinceList;
import com.bs.regsystemapi.service.AreaService;
import com.bs.regsystemapi.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qpj
 * @date 2022/4/24 16:48
 */
@RestController
@RequestMapping("/area")
@Api(value = "省市相关接口")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ApiOperation(value = "获取省份列表")
    @GetMapping("areas")
    public ResponseResult getProvinces() {
        List<ParentProvinceList> provinceList = areaService.getProvinceList();
        return ResponseResult.ok().put(provinceList);
    }
}
