package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.modal.dto.user.LogoutForm;

import java.util.List;

/**
 * @author qpj
 * @date 2022/2/25 16:01
 */
public interface LoginInfoService extends IService<LoginInfo> {
    void logoutInfo(LogoutForm form);
    List<LoginInfo> getLoginOnline(String userNo);
    List<LoginInfo> getLoginInfoList(String userNo);
}
