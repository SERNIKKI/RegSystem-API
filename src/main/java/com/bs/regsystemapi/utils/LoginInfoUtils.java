package com.bs.regsystemapi.utils;

import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.modal.dto.user.UserRequestForm;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/2/25 16:22
 */
public class LoginInfoUtils {
    public static LoginInfo getLoginInfo(HttpServletRequest request, Device device, UserEntity user, UserRequestForm form) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginInformation(request.getHeader("User-Agent"));
        loginInfo.setLoginTime(new Date());
        loginInfo.setInfoNo(NoGeneratorUtil.getNo());
        String ip = IpUtil.getIpAddr(request);
        loginInfo.setLoginIp(ip);
        if(ip.equals("127.0.0.1")) {
            loginInfo.setLoginAddress("本机");
        } else {
            loginInfo.setLoginAddress(IpToAddressUtil.getCityInfo(ip));
        }
        if(device.isMobile()) {
            loginInfo.setLoginEqui("手机");
        } else if(device.isTablet()) {
            loginInfo.setLoginEqui("平板");
        } else if(device.isNormal()) {
            loginInfo.setLoginEqui("PC");
        } else {
            loginInfo.setLoginEqui("其他");
        }
        if(user != null) {
            loginInfo.setUserNo(user.getUserNo());
            loginInfo.setLoginOnline(1L);
            loginInfo.setLoginState(1L);
            loginInfo.setLoginAccount(user.getUserName());
            loginInfo.setLoginRemark("登陆成功");
        } else {
            loginInfo.setLoginOnline(0L);
            loginInfo.setLoginState(0L);
            loginInfo.setLoginAccount(form.getUserName());
            loginInfo.setLoginRemark("登陆失败");
        }
        return loginInfo;
    }
}
