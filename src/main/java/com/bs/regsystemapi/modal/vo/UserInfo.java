package com.bs.regsystemapi.modal.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/2 15:08
 */
@Data
public class UserInfo implements Serializable {
    private String userName;

    private String userTel;

    private String userProvince;

    private String userAddress;

    private String userSex;

    private String userAvatar;

    private String userEmail;

    private String userRealName;

    private String userType;
}
