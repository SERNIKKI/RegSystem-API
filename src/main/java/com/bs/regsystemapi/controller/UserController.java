package com.bs.regsystemapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.entity.LoginInfo;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.user.FindPasswordForm;
import com.bs.regsystemapi.modal.dto.user.LogoutForm;
import com.bs.regsystemapi.modal.dto.user.QueryUserAccountForm;
import com.bs.regsystemapi.modal.dto.user.UserRequestForm;
import com.bs.regsystemapi.modal.vo.TreeData;
import com.bs.regsystemapi.modal.vo.user.User;
import com.bs.regsystemapi.modal.vo.user.UserAccountInfo;
import com.bs.regsystemapi.service.DepartmentService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.LoginInfoService;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author qpj
 * @Date 2022/02/09 15:40
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户api接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private LogService logService;
    @Autowired
    private DepartmentService departmentService;

    // 验证码
    public static String code;
    // 发送时间
    public static Date sendDate;
    // 需要验证的邮箱
    public static String email;

    @ApiOperation(value = "用户登录(勾选记住我)")
    @RequestMapping(value = "/loginByRemember", method = RequestMethod.POST)
    @Transactional
    public ResponseResult doLogin(@RequestBody UserRequestForm form,
                                  HttpServletRequest request,
                                  Device device) {
        if(form.getUserName().isEmpty() || form.getUserPassword().isEmpty()) {
            return ResponseResult.error("用户名或密码不能为空！");
        }
        String userPassword = form.getUserPassword();
        userPassword = EncodeUtils.encodeHex(userPassword.getBytes());
        form.setUserPassword(userPassword);
        User user = userService.getUserInfoByLogin(form);
        // 登陆时判断库中是否存在已登录
        List<LoginInfo> infoList = loginInfoService.getLoginOnline(user.getUserNo());
        for(LoginInfo info : infoList) {
            LogoutForm form1 = new LogoutForm();
            form1.setLoginOnline(0L);
            form1.setLogoutTime(new Date());
            form1.setInfoNo(info.getInfoNo());
            loginInfoService.logoutInfo(form1);
        }
        // 保存登录信息
        LoginInfo loginInfo = LoginInfoUtils.getLoginInfo(request, device, user, form);
        loginInfoService.save(loginInfo);
        // 判断是否存在部门
        if(!StringUtils.isEmpty(user.getUserDepartment())) {
            Department departmentInfo = departmentService.getDepartmentInfo(user.getUserDepartment());
            user.setDepartment(departmentInfo.getSecondDepartment());
        }
        if(user != null) {
            StpUtil.login(user.getUserNo(), new SaLoginModel()
                    .setDevice("PC") // 同端登录强制下线
                    .setTimeout(60 * 60 * 24 * 7)  // 7天后失效
                    .setIsLastingCookie(true)); // 持久化cookie
            user.setLoginNo(loginInfo.getInfoNo());
            StpUtil.getSession().set("user", user);
            return ResponseResult.ok().put(StpUtil.getTokenInfo());
        }
        return ResponseResult.error("登录失败,用户不存在！");
    }

    @ApiOperation(value = "用户登录(不勾选记住我)")
    @PostMapping("/login")
    @Transactional
    public ResponseResult doLogin2(@RequestBody UserRequestForm form,
                                   HttpServletRequest request,
                                   Device device) {
        if(form.getUserName().isEmpty() || form.getUserPassword().isEmpty()) {
            return ResponseResult.error("用户名或密码不能为空！");
        }
        String userPassword = form.getUserPassword();
        userPassword = EncodeUtils.encodeHex(userPassword.getBytes());
        form.setUserPassword(userPassword);
        User user = userService.getUserInfoByLogin(form);
        // 登陆时判断库中是否存在已登录
        List<LoginInfo> infoList = loginInfoService.getLoginOnline(user.getUserNo());
        for(LoginInfo info : infoList) {
            LogoutForm form1 = new LogoutForm();
            form1.setLoginOnline(0L);
            form1.setLogoutTime(new Date());
            form1.setInfoNo(info.getInfoNo());
            loginInfoService.logoutInfo(form1);
        }
        LoginInfo loginInfo = LoginInfoUtils.getLoginInfo(request, device, user, form);
        loginInfoService.save(loginInfo);
        // 判断是否存在部门
        if(!StringUtils.isEmpty(user.getUserDepartment())) {
            Department departmentInfo = departmentService.getDepartmentInfo(user.getUserDepartment());
            user.setDepartment(departmentInfo.getSecondDepartment());
        }
        if(user != null) {
            StpUtil.login(user.getUserNo());
            user.setLoginNo(loginInfo.getInfoNo());
            StpUtil.getSession().set("user", user);
            return ResponseResult.ok().put(StpUtil.getTokenInfo());
        }
        return ResponseResult.error("登录失败,用户不存在！");
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult insertUser(@RequestBody UserEntity user) {
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPassword())) {
            return ResponseResult.error("用户名或密码为空！");
        } else {
            String userPassword = user.getUserPassword();
            // 加密
            userPassword = EncodeUtils.encodeHex(userPassword.getBytes());
            user.setUserPassword(userPassword);
            user.setCreateTime(new Date());
            user.setUserNo(NoGeneratorUtil.getNo());
            userService.save(user);
            return ResponseResult.ok();
        }
    }

    /**
     * type: true: 需要验证邮箱在数据存在，false: 不需要验证邮箱是否在数据库存在
     */
    @ApiOperation(value = "发送验证码")
    @GetMapping("/sendCode/{userEmail}/{type}/{subject}")
    public ResponseResult sendCode(@PathVariable("userEmail") String userEmail,
                                    @PathVariable("type") String type,
                                   @PathVariable("subject") String subject) {
        if(StringUtils.isNotEmpty(code) && sendDate != null && TimeUtils.calLastedTime(sendDate) <= 60) {
            return ResponseResult.error("发送验证码的间隔不能小于60秒");
        }
        if(StringUtils.isEmpty(userEmail)) {
            return ResponseResult.error("用户邮箱不能为空");
        }
        if(!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", userEmail)) {
            return ResponseResult.error("邮箱格式不正确");
        }
        int num = userService.findUserByEmail(userEmail);
        if(num == 0 && type.equals("true")) {
            return ResponseResult.error("该用户邮箱不存在");
        }
        // 开始发送验证码
        email = userEmail;
        code = RandomUtils.getrandom();
        MailSenderUtil mailSenderUtil = new MailSenderUtil(subject,userEmail,code);
        try {
            mailSenderUtil.sendEmail();
            sendDate = new Date();
            return ResponseResult.ok();
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "找回密码")
    @PostMapping(value = "/findPassword")
    public ResponseResult foundPassword(@RequestBody FindPasswordForm form) {
        if(StringUtils.isEmpty(code) || sendDate == null) {
            return ResponseResult.error("验证码为空，请先发送验证码");
        }
        if(TimeUtils.calLastedTime(sendDate) > 60) {
            return ResponseResult.error("验证码已失效");
        }
        if(!code.equals(form.getCode())) {
            return ResponseResult.error("验证码错误");
        }
        if(!email.equals(form.getUserEmail())) {
            return ResponseResult.error("邮箱与发送验证码的邮箱不一致");
        }
        String userPassword = form.getUserPassword();
        userPassword = EncodeUtils.encodeHex(userPassword.getBytes());
        form.setUserPassword(userPassword);
        userService.updatePasswordByEmail(form);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "检查验证码是否正确")
    @GetMapping("/checkCode/{formCode}/{userEmail}")
    public ResponseResult checkCode(@PathVariable("formCode") String formCode,
                                    @PathVariable("userEmail") String userEmail) {
        if(StringUtils.isEmpty(code) || sendDate == null) {
            return ResponseResult.error("验证码为空，请先发送验证码");
        }
        if(TimeUtils.calLastedTime(sendDate) > 60) {
            return ResponseResult.error("验证码已失效");
        }
        if(!code.equals(formCode)) {
            return ResponseResult.error("验证码错误");
        }
        if(!email.equals(userEmail)) {
            return ResponseResult.error("邮箱与发送验证码的邮箱不一致");
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "绑定的邮箱是否存在")
    @GetMapping("/isHaveEmail/{userEmail}")
    public ResponseResult isHaveEmail(@PathVariable("userEmail") String userEmail) {
        if(!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", userEmail)) {
            return ResponseResult.error("邮箱格式不正确");
        }
        int num = userService.findUserByEmail(userEmail);
        if(num == 0) {
            return ResponseResult.ok().put("false");
        }
        return ResponseResult.ok().put("true");
    }

    @ApiOperation(value = "更改用户信息")
    @SaCheckLogin
    @PostMapping("/updateUserInfo")
    public ResponseResult updateUser(@RequestBody UserEntity user) {
        if(StringUtils.isEmpty(user.getUserNo())) {
            return ResponseResult.error("用户不存在");
        }
        userService.updateUser(user);
        user = userService.getUserInfo(user.getUserNo());
        return ResponseResult.ok().put(user);
    }

    @ApiOperation(value = "查询登录状态")
    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    public ResponseResult isLogin() {
        return ResponseResult.ok().put(StpUtil.isLogin());
    }

    @ApiOperation(value = "获取token信息")
    @SaCheckLogin
    @RequestMapping(value = "/tokenInfo", method = RequestMethod.GET)
    public ResponseResult getTokenInfo() {
        return ResponseResult.ok().put(StpUtil.getTokenInfo());
    }

    @ApiOperation(value = "获取session信息")
    @RequestMapping(value = "/sessionInfo/{key}", method = RequestMethod.GET)
    public ResponseResult getSession(@PathVariable("key") String key) {
        Object session = StpUtil.getSession().get(key);
        return ResponseResult.ok().put(session);
    }

    @ApiOperation(value = "写入session信息")
    @GetMapping("/setSession")
    public ResponseResult setSession(@RequestParam String key,@RequestParam Object value) {
        StpUtil.getSession().set(key, value);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "注销登录")
    @SaCheckLogin
    @RequestMapping(value = "/logout/{loginNo}", method = RequestMethod.GET)
    public ResponseResult logout(@PathVariable("loginNo") String infoNo) {
        LogoutForm form = new LogoutForm();
        form.setInfoNo(infoNo);
        form.setLogoutTime(new Date());
        form.setLoginOnline(0L);
        loginInfoService.logoutInfo(form);
        StpUtil.logout();
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/totalUser")
    @Transactional
    public ResponseResult getAllUser() {
        List<TreeData> totalUser = userService.getTotalUser();
        return ResponseResult.ok().put(totalUser);
    }

    @Transactional
    @ApiOperation(value = "获取账号信息列表")
    @PostMapping("/getAccountInfos")
    public ResponseResult getAccountInfos(@RequestBody QueryUserAccountForm form) {
        ManagePageResult accountInfo = userService.getAccountInfos(form);
        return ResponseResult.ok().put(accountInfo);
    }

    @ApiOperation(value = "删除账号")
    @GetMapping("/delete/{userNo}")
    public ResponseResult deleteUser(@PathVariable("userNo") String userNo) {
        if(StringUtils.isEmpty(userNo)) {
            return ResponseResult.error("no不能为空");
        }
        boolean b = userService.deleteDoctor(userNo);
        if(b) {
            logService.addLog(Action.DELETE, Table.USER, userNo);
        } else {
            logService.addLog(Action.DELETE, Table.USER, userNo, Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation("踢人下线")
    @GetMapping("/remove/{userNo}/{infoNo}")
    public ResponseResult removeLogin(@PathVariable("userNo") String userNo, @PathVariable("infoNo") String infoNo) {
        LogoutForm form = new LogoutForm();
        form.setInfoNo(infoNo);
        form.setLogoutTime(new Date());
        form.setLoginOnline(0L);
        loginInfoService.logoutInfo(form);
        StpUtil.logout(userNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取账号信息")
    @GetMapping("/getAccountInfo/{userNo}")
    public ResponseResult getAccountInfo(@PathVariable("userNo") String userNo) {
        if(StringUtils.isEmpty(userNo)) {
            return ResponseResult.error("用户no不能为空！");
        }
        UserAccountInfo accountInfo = userService.getAccountInfo(userNo);
        return ResponseResult.ok().put(accountInfo);
    }
}
