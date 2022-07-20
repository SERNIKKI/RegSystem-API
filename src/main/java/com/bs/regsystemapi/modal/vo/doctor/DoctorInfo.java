package com.bs.regsystemapi.modal.vo.doctor;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/25 11:50
 */
@Data
public class DoctorInfo implements Serializable {

    private String userNo;

    private String userTel;

    private String userProvince;

    private String userAddress;

    private String userSex;

    private String userAvatar;

    private String userEmail;

    private String userRealName;

    private String userType;

    private String userDepartment;

    private String userPosition;

    private String userLocation;

    private String userSpecialties;

    private String userWorkAvatar;

    private String department;

    private String secondDepartment;

    private int loginOnline;

    private String reservePrice;
}
