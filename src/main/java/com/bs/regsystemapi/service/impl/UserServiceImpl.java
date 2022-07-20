package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.UserDao;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorForm;
import com.bs.regsystemapi.modal.dto.doctor.QueryDoctorsForm;
import com.bs.regsystemapi.modal.dto.user.FindPasswordForm;
import com.bs.regsystemapi.modal.dto.user.QueryUserAccountForm;
import com.bs.regsystemapi.modal.dto.user.UserRequestForm;
import com.bs.regsystemapi.modal.vo.TreeData;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugLabelInfo;
import com.bs.regsystemapi.modal.vo.drug.DrugTypeInfo;
import com.bs.regsystemapi.modal.vo.user.User;
import com.bs.regsystemapi.modal.vo.user.UserAccountInfo;
import com.bs.regsystemapi.service.DepartmentService;
import com.bs.regsystemapi.service.LoginInfoService;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wybusy.EasyHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @DATA 2022/02/10
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public String getUserType(String userNo) {
        String userType = this.baseMapper.getUserType(userNo);
        return userType;
    }

    @Override
    public User getUserInfoByLogin(UserRequestForm form) {
        User user = this.baseMapper.getUserInfoByLogin(form);
        return user;
    }

    @Override
    public int findUserByEmail(String userEmail) {
        int num = this.baseMapper.findUserByEmail(userEmail);
        return num;
    }

    @Override
    public void updatePasswordByEmail(FindPasswordForm form) {
        this.baseMapper.updatePasswordByEmail(form);
    }

    @Override
    public void updateUser(UserEntity user) {
        this.baseMapper.updateUser(user);
    }

    @Override
    public UserEntity getUserInfo(String userNo) {
        UserEntity user = this.baseMapper.getUserInfo(userNo);
        return user;
    }

    @Override
    public UserInfo getBaseUserInfo(String userNo) {
        return this.baseMapper.getBaseUserInfo(userNo);
    }

    @Override
    public ManagePageResult getDoctorList(QueryDoctorForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DoctorInfo> doctorList = this.baseMapper.getDoctorList(form);
        PageInfo<DoctorInfo> doctorInfoPageInfo = new PageInfo<>(doctorList);
        List<DoctorInfo> list = doctorInfoPageInfo.getList();
        for(DoctorInfo doctorInfo : list) {
            List<LoginInfo> loginOnline = loginInfoService.getLoginOnline(doctorInfo.getUserNo());
            if(loginOnline != null && loginOnline.size() > 0) {
                doctorInfo.setLoginOnline(1);
            } else {
                doctorInfo.setLoginOnline(0);
            }
        }
        return ManagePageResult.getPageResult(doctorInfoPageInfo);
    }

    @Override
    public DoctorInfo getDoctorInfo(String userNo) {
        DoctorInfo doctorInfo = this.baseMapper.getDoctorInfo(userNo);
        if(!StringUtils.isEmpty(doctorInfo.getUserDepartment())) {
            Department departmentInfo = departmentService.getDepartmentInfo(doctorInfo.getUserDepartment());
            if(departmentInfo != null) {
                doctorInfo.setDepartment(departmentInfo.getDepartment());
                doctorInfo.setSecondDepartment(departmentInfo.getSecondDepartment());
            }
        }
        return doctorInfo;
    }

    @Override
    public List<DoctorInfo> getDoctorsInfo(QueryDoctorsForm form) {
        List<DoctorInfo> doctorsInfo = this.baseMapper.getDoctorsInfo(form);
        return doctorsInfo;
    }

    @Override
    public boolean deleteDoctor(String userNo) {
        int i = this.baseMapper.deleteDoctor(userNo);
        return i > 0;
    }

    @Override
    public boolean recoverDoctor(String userNo) {
        int i = this.baseMapper.recoverDoctor(userNo);
        return i > 0;
    }

    @Override
    public List<DoctorInfo> getDeleteList() {
        List<DoctorInfo> deleteList = this.baseMapper.getDeleteList();
        return deleteList;
    }

    @Override
    public List<TreeData> getTotalUser() {
        List<TreeData> dataList = new ArrayList<>();
        TreeData admin = new TreeData();
        TreeData doctor = new TreeData();
        // 为admin灌数据
        admin.setValue("admin");
        admin.setLabel("管理员");
        List<UserEntity> allAdmin = this.baseMapper.getAdmin();
        List<TreeData> adminChildren = new ArrayList<>();
        for(UserEntity user : allAdmin) {
            TreeData adminFirst = new TreeData();
            adminFirst.setValue(user.getUserNo());
            adminFirst.setLabel(user.getUserRealName());
            adminChildren.add(adminFirst);
        }
        admin.setChildren(adminChildren);
        // 为doctor灌数据
        doctor.setValue("doctor");
        doctor.setLabel("医生");
        List<DrugTypeInfo> typeInfo = departmentService.getTypeInfo();
        List<TreeData> children1 = new ArrayList<>();
        for(DrugTypeInfo drugTypeInfo : typeInfo) {
            TreeData first = new TreeData();
            first.setValue(drugTypeInfo.getValue());
            first.setLabel(drugTypeInfo.getLabel());
            List<DrugLabelInfo> children = drugTypeInfo.getChildren();
            List<TreeData> children2 = new ArrayList<>();
            for(DrugLabelInfo drugLabelInfo : children) {
                TreeData second = new TreeData();
                second.setValue(drugLabelInfo.getValue());
                second.setLabel(drugLabelInfo.getLabel());
                QueryDoctorsForm form = new QueryDoctorsForm();
                form.setUserDepartment(second.getValue());
                List<DoctorInfo> doctorsInfo = this.getDoctorsInfo(form);
                List<TreeData> children3 = new ArrayList<>();
                for(DoctorInfo doctorInfo : doctorsInfo) {
                    TreeData third = new TreeData();
                    third.setValue(doctorInfo.getUserNo());
                    third.setLabel(doctorInfo.getUserRealName());
                    children3.add(third);
                }
                second.setChildren(children3);
                children2.add(second);
            }
            first.setChildren(children2);
            children1.add(first);
        }
        doctor.setChildren(children1);
        dataList.add(admin);
        dataList.add(doctor);
        return dataList;
    }

    @Override
    public ManagePageResult getAccountInfos(QueryUserAccountForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<UserAccountInfo> accountInfo = this.baseMapper.getAccountInfos(form);
        PageInfo<UserAccountInfo> userAccountInfoPageInfo = new PageInfo<>(accountInfo);
        return ManagePageResult.getPageResult(userAccountInfoPageInfo);
    }

    @Override
    public UserAccountInfo getAccountInfo(String userNo) {
        return this.baseMapper.getAccountInfo(userNo);
    }
}
