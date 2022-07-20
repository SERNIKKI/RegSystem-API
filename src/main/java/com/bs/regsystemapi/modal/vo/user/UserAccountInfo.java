package com.bs.regsystemapi.modal.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAccountInfo implements Serializable {

    private String userNo;

    private String userRealName;

    private String userName;

    private String loginIp;

    private String loginAddress;

    private String loginTime;

    private String loginState;

    private String loginEqui;

    private String loginOnline;

    private String loginRemark;

    private String loginAccount;

    private String userPassword;

    private String infoNo;

}
