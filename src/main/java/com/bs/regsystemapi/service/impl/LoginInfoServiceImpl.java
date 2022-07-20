package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.LoginInfoDao;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.modal.dto.user.LogoutForm;
import com.bs.regsystemapi.service.LoginInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/2/25 16:00
 */
@Service("loginInfoService")
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoDao, LoginInfo> implements LoginInfoService{

    @Override
    public void logoutInfo(LogoutForm form) {
        this.baseMapper.logoutInfo(form);
    }

    @Override
    public List<LoginInfo> getLoginOnline(String userNo) {
        List<LoginInfo> list = this.baseMapper.getLoginOnline(userNo);
        return list;
    }

    @Override
    public List<LoginInfo> getLoginInfoList(String userNo) {
        List<LoginInfo> list = this.baseMapper.getLoginInfoList(userNo);
        return list;
    }
}
