package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorForm;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorsForm;
import com.bs.regsystemapi.modal.dto.user.FindPasswordForm;
import com.bs.regsystemapi.modal.dto.user.QueryUserAccountForm;
import com.bs.regsystemapi.modal.dto.user.UserRequestForm;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.user.User;
import com.bs.regsystemapi.modal.vo.user.UserAccountInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @DATA 2022/02/09
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    // 用户相关
    String getUserType(String userNo);
    User getUserInfoByLogin(UserRequestForm form);
    void updateUser(UserEntity user);
    int findUserByEmail(String userEmail);
    void updatePasswordByEmail(FindPasswordForm form);
    UserEntity getUserInfo(String userNo);
    UserInfo getBaseUserInfo(String userNo);
    List<UserEntity> getAdmin();
    // 医生相关
    List<DoctorInfo> getDoctorList(QueryDoctorForm form);
    DoctorInfo getDoctorInfo(String userNo);
    List<DoctorInfo> getDoctorsInfo(QueryDoctorsForm form);
    int deleteDoctor(String userNo);
    int recoverDoctor(String userNo);
    List<DoctorInfo> getDeleteList();
    List<UserAccountInfo> getAccountInfos(QueryUserAccountForm form);
    UserAccountInfo getAccountInfo(String userNo);
}
