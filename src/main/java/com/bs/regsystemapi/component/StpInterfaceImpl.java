package com.bs.regsystemapi.component;

import cn.dev33.satoken.stp.StpInterface;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;
    /**
     *
     * @param loginId
     * @param loginType
     * @return 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 获取用户类型
        String userType = "";
        List<String> list = new ArrayList<String>();
        if(!StringUtils.isEmpty(String.valueOf(loginId))) {
            userType = userService.getUserType(String.valueOf(loginId));
        }
        if(userType.equals("admin")) {
            list.add("user-add");
            list.add("user-delete");
            list.add("user-update");
            list.add("user-get");
        }
        return list;
    }

    /**
     *
     * @param loginId
     * @param loginType
     * @return 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        String userType = "";
        if(!StringUtils.isEmpty(String.valueOf(loginId))) {
            userType = userService.getUserType(String.valueOf(loginId));
        }
        list.add(userType);
        return list;
    }
}
