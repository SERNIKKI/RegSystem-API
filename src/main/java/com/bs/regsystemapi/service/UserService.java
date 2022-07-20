package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorForm;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorsForm;
import com.bs.regsystemapi.modal.dto.user.FindPasswordForm;
import com.bs.regsystemapi.modal.dto.user.QueryUserAccountForm;
import com.bs.regsystemapi.modal.dto.user.UserRequestForm;
import com.bs.regsystemapi.modal.vo.TreeData;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.modal.vo.user.User;
import com.bs.regsystemapi.modal.vo.user.UserAccountInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @DATA 2022/02/10
 */
public interface UserService extends IService<UserEntity> {

    String getUserType(String userNo);
    User getUserInfoByLogin(UserRequestForm form);
    int findUserByEmail(String userEmail);
    void updatePasswordByEmail(FindPasswordForm form);
    void updateUser(UserEntity user);
    UserEntity getUserInfo(String userNo);
    UserInfo getBaseUserInfo(String userNo);
    ManagePageResult getDoctorList(QueryDoctorForm form);
    DoctorInfo getDoctorInfo(String userNo);
    List<DoctorInfo> getDoctorsInfo(QueryDoctorsForm form);
    boolean deleteDoctor(String userNo);
    boolean recoverDoctor(String userNo);
    List<DoctorInfo> getDeleteList();
    List<TreeData> getTotalUser();
    ManagePageResult getAccountInfos(QueryUserAccountForm form);
    UserAccountInfo getAccountInfo(String userNo);
}
