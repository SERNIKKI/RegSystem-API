package com.bs.regsystemapi.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.LogDao;
import com.bs.regsystemapi.entity.Log;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.modal.dto.log.QueryLogForm;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/23 21:43
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements LogService {

    @Autowired
    private LogService logService;

    @Override
    public void addLog(String action, String effectTable, String effectNos, String status) {
        if(!StpUtil.isLogin()) {
            return;
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        Log log = new Log();
        log.setAction(action);
        log.setEffectTable(effectTable);
        log.setEffectNos(effectNos);
        log.setStatus(status);
        log.setCreateName(user.getUserRealName());
        log.setCreateTime(new Date());
        log.setUserType(user.getUserType());
        log.setLogNo(NoGeneratorUtil.getNo());
        logService.save(log);
    }

    @Override
    public void addLog(String action, String effectTable, String effectNos) {
        if(!StpUtil.isLogin()) {
            return;
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        Log log = new Log();
        log.setAction(action);
        log.setEffectTable(effectTable);
        log.setEffectNos(effectNos);
        log.setStatus(Status.SUCCESS);
        log.setCreateName(user.getUserRealName());
        log.setCreateTime(new Date());
        log.setUserType(user.getUserType());
        log.setLogNo(NoGeneratorUtil.getNo());
        logService.save(log);
    }

    @Override
    public void addLog(String action) {
        if(!StpUtil.isLogin()) {
            return;
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        Log log = new Log();
        log.setAction(action);
        log.setStatus(Status.SUCCESS);
        log.setCreateName(user.getUserRealName());
        log.setCreateTime(new Date());
        log.setUserType(user.getUserType());
        log.setLogNo(NoGeneratorUtil.getNo());
        logService.save(log);
    }

    @Override
    public ManagePageResult getLogList(QueryLogForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Log> logList = this.baseMapper.getLogList(form);
        PageInfo<Log> logPageInfo = new PageInfo<>(logList);
        return ManagePageResult.getPageResult(logPageInfo);
    }
}
