package com.bs.regsystemapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bs.regsystemapi.modal.dto.QuerySuggestionForm;
import com.bs.regsystemapi.utils.IpToAddressUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author qpj
 * @date 2022/3/25 21:18
 */
@RestController
@RequestMapping("/district")
@Api(value = "获取行政区接口")
public class ProvinceController {

    @ApiOperation(value = "获取指定行政区划的子级行政区划")
    @GetMapping(value = "/children")
    public ResponseResult getChildren(@RequestParam(value = "id", required = false) String id) {
        String provinceList = IpToAddressUtil.getProvinceList(id);
        JSONObject json = JSON.parseObject(provinceList);
        return ResponseResult.ok().put(json);
    }

    @ApiOperation(value = "地址信息关键词提示")
    @PostMapping(value = "/suggestion")
    public ResponseResult getSuggestion(@RequestBody QuerySuggestionForm form) {
        String provinceList = IpToAddressUtil.getSuggestion(form);
        JSONObject json = JSON.parseObject(provinceList);
        return ResponseResult.ok().put(json);
    }
}
