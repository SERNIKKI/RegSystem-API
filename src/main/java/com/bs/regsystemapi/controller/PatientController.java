package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.regsystemapi.entity.PatientEntity;
import com.bs.regsystemapi.modal.dto.patient.GetPatientListForm;
import com.bs.regsystemapi.modal.dto.patient.PatientRequestForm;
import com.bs.regsystemapi.service.PatPersonService;
import com.bs.regsystemapi.service.PatientService;
import com.bs.regsystemapi.utils.EncodeUtils;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.WxUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient")
@Api(value = "患者api接口")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatPersonService patPersonService;

    @ApiOperation(value = "患者登录(wx授权)")
    @RequestMapping(value = "/patientLogin", method = RequestMethod.GET)
    @Transactional
    public ResponseResult doPatientLogin(@RequestParam("code") String code) {
        Map<String, Object> result = new HashMap<>();
        JSONObject jsonObject = WxUtils.getWxResult(code);
        System.out.println(jsonObject);
        if (jsonObject.get("errcode") != null) {
            return ResponseResult.error(jsonObject.toJSONString());
        }

        String sessionKey = jsonObject.get("session_key").toString();
        String openId = jsonObject.get("openid").toString();
        PatientEntity patient = this.patientService.selectByOpenId(openId);
        if (patient == null) {
            result.put("is_register_code", "0");
            result.put("is_register", "未注册");
            result.put("open_id", openId);
            return ResponseResult.ok().put(result);
        }
        StpUtil.login(openId);
        result.put("patient", patient);
        result.put("token", StpUtil.getTokenValueByLoginId(patient.getPatNo()));
        return ResponseResult.ok().put(result);
    }

    @ApiOperation(value = "患者登录(账号和密码)")
    @RequestMapping(value = "/patientLoginByAccount", method = RequestMethod.POST)
    @Transactional
    public ResponseResult doPatientLoginByAccount(@RequestBody @Validated PatientRequestForm form) {
        if (form.getPatAccount().isEmpty() || form.getPatPassword().isEmpty()) {
            return ResponseResult.error("账号或密码不能为空！");
        }

        PatientEntity accountResult = patientService.getOne(new QueryWrapper<PatientEntity>().eq("pat_account", form.getPatAccount()));
        if (accountResult == null) {
            return ResponseResult.error("登录失败,用户名不存在！");
        }

        // 密码加密
        String patPassword = form.getPatPassword();
        patPassword = EncodeUtils.encodeHex(patPassword.getBytes());
        form.setPatPassword(patPassword);
        PatientEntity patient = patientService.getPatInfoByLogin(form);
        Map<String, Object> result = new HashMap<>(10);
        if (patient != null) {
            StpUtil.login(patient.getPatNo());
            result.put("patient", patient);
            result.put("token", StpUtil.getTokenValueByLoginId(patient.getPatNo()));
            return ResponseResult.ok().put(result);
        }
        return ResponseResult.error("登录失败,密码错误！");
    }


    @ApiOperation(value = "患者注册")
    @RequestMapping(value = "/patientRegister", method = RequestMethod.POST)
    public ResponseResult patientRegister(@RequestBody @Validated PatientEntity patient) {
        String patPassword = patient.getPatPassword();
        // 加密
        patPassword = EncodeUtils.encodeHex(patPassword.getBytes());
        patient.setPatPassword(patPassword);

        PatientEntity patientEntity = patientService.getOne(
                new QueryWrapper<PatientEntity>()
                        .eq("pat_account", patient.getPatAccount())
        );
        // 账号已存在
        if (patientEntity != null) {
            return ResponseResult.error("该账号已存在!");
        }
        patientService.save(patient);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取患者列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult getPatientList(@RequestBody GetPatientListForm form) {
        ManagePageResult patients = patientService.getPatientList(form);
        return ResponseResult.ok().put(patients);
    }


}
