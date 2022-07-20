package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.modal.dto.user.LogoutForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022-02-25
 */
@Mapper
public interface LoginInfoDao extends BaseMapper<LoginInfo> {
    void logoutInfo(LogoutForm form);
    List<LoginInfo> getLoginOnline(String userNo);
    List<LoginInfo> getLoginInfoList(String userNo);
}
