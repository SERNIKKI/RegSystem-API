package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.service.PatPersonService;
import com.bs.regsystemapi.utils.RandomUtils;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import com.bs.regsystemapi.utils.ThreadLocalUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2022/4/24 23:01
 */
@RestController
@RequestMapping("/patPerson")
@Api(value = "就诊人api接口")
@Slf4j
public class PatPersonController {
    @Autowired
    private PatPersonService patPersonService;
    @ApiOperation(value = "根据ID获取就诊人")
    @RequestMapping(value = "/getPatientPersonById", method = RequestMethod.GET)
    public ResponseResult getPatientPersonById(@RequestParam("id") String id) {
        PatPerson patPerson = patPersonService.getById(id);
        return ResponseResult.ok().put(patPerson);
    }

    @ApiOperation(value = "根据用户号获取就诊人", notes = "请求头传递token即可")
    @RequestMapping(value = "/getPatientPerson", method = RequestMethod.GET)
    public ResponseResult getPatientPerson() {
        QueryWrapper<PatPerson> wrapper = new QueryWrapper<>();
        try {
            String patNo = StpUtil.getLoginIdByToken(ThreadLocalUtils.get("Authorization").toString()).toString();
            log.info("用户号:" + patNo);
            // 获取用户下所有就诊人
            List<PatPerson> patPersonList = patPersonService.getBaseMapper().selectList(wrapper.eq("pat_no", patNo));
            return ResponseResult.ok().put(patPersonList);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
    }


    @ApiOperation(value = "就诊人详情", notes = "请求头传递token即可")
    @RequestMapping(value = "/getPatientPersonDetail", method = RequestMethod.GET)
    public ResponseResult getPatientPersonDetail(@RequestParam("personNo") String personNo) {
        PatPerson patPerson = patPersonService.getOne(
                new QueryWrapper<PatPerson>().eq("person_no", personNo)
        );
        return ResponseResult.ok().put(patPerson);
    }

    @ApiOperation(value = "添加就诊人")
    @RequestMapping(value = "/savePatPerson", method = RequestMethod.POST)
    public ResponseResult savePatPerson(@RequestBody @Validated PatPerson patPerson) {
        patPerson.setIsDelete("1");
        // 生成就诊卡号(4位随机数+10位时间戳)
        String time = Long.toString(System.currentTimeMillis());
        time = time.substring(0, time.length()-3);
        String personNo = RandomUtils.getrandom(4) + time;
        patPerson.setPersonNo(personNo);
        // 获取patNo
        try {
            String patNo = (String) StpUtil.getLoginIdByToken((String) ThreadLocalUtils.get("Authorization"));
            log.info("用户号:" + patNo);
            patPerson.setPatNo(patNo);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
        patPersonService.save(patPerson);
        return ResponseResult.ok();
    }


    @ApiOperation(value = "更改就诊卡")
    @PostMapping("/update")
    public ResponseResult updatePerson(@RequestBody PatPerson patPerson) {
        if(StringUtils.isEmpty(patPerson.getPersonNo())) {
            return ResponseResult.error("就诊卡no不能为空！");
        }
        patPersonService.updatePerson(patPerson);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除就诊卡")
    @PostMapping("/delete")
    public ResponseResult deletePerson(@RequestBody String personNo) {
        if(StringUtils.isEmpty(personNo)) {
            return ResponseResult.error("就诊卡no不能为空！");
        }
        patPersonService.deletePerson(personNo);
        return ResponseResult.ok();
    }
}
