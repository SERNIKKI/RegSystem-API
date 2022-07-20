package com.bs.regsystemapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.LoginInfoService;
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
 * @date 2022/2/25 21:15
 */
@RestController
@RequestMapping("/loginInfo")
@Api(value = "历史记录api接口")
public class LoginInfoController {
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取前50条历史记录")
    @SaCheckLogin
    @GetMapping("/getLoginInfoList/{userNo}")
    public ResponseResult getLoginInfoList(@PathVariable("userNo") String userNo) {
        List<LoginInfo> infoList = loginInfoService.getLoginInfoList(userNo);
        return ResponseResult.ok().put(infoList);
    }
}
