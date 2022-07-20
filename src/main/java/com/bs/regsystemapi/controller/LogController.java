package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Log;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.modal.dto.log.QueryLogForm;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/23 21:44
 */
@RestController
@RequestMapping("/log")
@Api(value = "系统日志相关")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取系统操作日志")
    @PostMapping("/list")
    public ResponseResult getLogList(@RequestBody QueryLogForm form) {
        ManagePageResult logList = logService.getLogList(form);
        return ResponseResult.ok().put(logList);
    }

    @ApiOperation(value = "删除日志")
    @PostMapping("/delete")
    public ResponseResult deleteLog(@RequestBody Log log) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行系统日志记录删除操作，日志no为{}",user.getUserName(),log.getLogNo());
        logService.removeById(log.getId());
        return ResponseResult.ok();
    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<Log> logList) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        List<String> nos = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for(Log log : logList) {
            ids.add(log.getId());
            nos.add(log.getLogNo());
        }
        logService.removeByIds(ids);
        logger.error("管理员{}正在进行系统日志记录删除操作，日志no为{}",user.getUserName(),nos.toString());
        return ResponseResult.ok();
    }
}
