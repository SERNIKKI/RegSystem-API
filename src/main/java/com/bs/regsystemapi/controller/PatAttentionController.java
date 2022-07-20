package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bs.regsystemapi.entity.PatAttention;
import com.bs.regsystemapi.modal.dto.patattention.GetMyAttention;
import com.bs.regsystemapi.modal.dto.patattention.MyAttentionInfo;
import com.bs.regsystemapi.modal.vo.doctor.DoctorInfo;
import com.bs.regsystemapi.service.PatAttentionService;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.RandomUtils;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import com.bs.regsystemapi.utils.ThreadLocalUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Date 2022/4/26 22:08
 */
@RestController
@RequestMapping("/patAttention")
@Api(value = "患者关注api接口")
@Slf4j
public class PatAttentionController {

    @Autowired
    private PatAttentionService patAttentionService;
    @Autowired
    private UserService doctorService;

    @ApiOperation(value = "获取我的关注(医生No/患者No)")
    @RequestMapping(value = "/getMyPatAttention", method = RequestMethod.GET)
    public ResponseResult getMyPatAttention(@RequestParam(value = "doctorNo", required = false) String doctorNo) {
        QueryWrapper<PatAttention> wrapper = new QueryWrapper<>();
        String patNo = "";
        try {
            patNo = StpUtil.getLoginIdByToken(ThreadLocalUtils.get("Authorization").toString()).toString();
            log.info("用户号:" + patNo);
        } catch (NullPointerException e) {
            log.info("请求头参数Authorization为空!");
        }
        Map<String, Object> result = new HashMap<>();


        // 参数: 患者No
        if (StringUtils.isNotEmpty(patNo)) {
            wrapper.eq("pat_no", patNo);
            // 参数: 患者No+医生No
            if (StringUtils.isNotEmpty(doctorNo)) {
                wrapper.eq("user_no", doctorNo);
                // 确认患者是否关注了该医生，返回状态码即可
                PatAttention patPerson = patAttentionService.getOne(wrapper);
                if (patPerson == null || "0".equals(patPerson.getStatus())) {
                    // 该用户与医生从未关注过
                    result.put("isAttention", "0");
                    result.put("isAttentionStr", "未关注");
                    return ResponseResult.ok().put(result);
                }
                result.put("isAttention", "1");
                result.put("isAttentionStr", "已关注");
                result.put("patPerson", patPerson);
                return ResponseResult.ok().put(result);
            } else {
                List<PatAttention> patPersonList = patAttentionService.getBaseMapper().selectList(wrapper);
                List<MyAttentionInfo> resultList = new ArrayList<>();
                MyAttentionInfo info;
                for (PatAttention patAttention : patPersonList) {
                    info = new MyAttentionInfo();
                    DoctorInfo doctorInfo = doctorService.getDoctorInfo(patAttention.getUserNo());
                    // 医生科室
                    info.setUserDepartment(doctorInfo.getSecondDepartment());
                    // 医生姓名
                    info.setUserRealName(doctorInfo.getUserRealName());
                    // 医生职称
                    info.setUserPosition(doctorInfo.getUserPosition());
                    BeanUtils.copyProperties(patAttention, info);
                    resultList.add(info);
                }
                return ResponseResult.ok().put(resultList);
            }
        } else {
            // 参数：医生No
            if (StringUtils.isNotEmpty(doctorNo)) {
                wrapper.eq("user_no", doctorNo);
                List<PatAttention> patPersonList = patAttentionService.getBaseMapper().selectList(wrapper);
                return ResponseResult.ok().put(patPersonList);
            } else {
                return ResponseResult.error("请传入医生No或患者No，不可不传!");
            }
        }
    }


    @ApiOperation(value = "添加关注")
    @RequestMapping(value = "/savePatAttention", method = RequestMethod.POST)
    public ResponseResult savePatPerson(@RequestBody GetMyAttention myAttention) {
        PatAttention patAttention = new PatAttention();
        String patNo = "";
        try {
            // 获取patNo
            patNo = (String) StpUtil.getLoginIdByToken((String) ThreadLocalUtils.get("Authorization"));
            log.info("用户号:" + patNo);
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
        patAttention.setPatNo(patNo);
        // 通过患者编号和医生编号查询关注记录，判断是否已关注
        QueryWrapper<PatAttention> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pat_no", patNo);
        queryWrapper.eq("user_no", myAttention.getDoctorNo());
        PatAttention attention = patAttentionService.getOne(queryWrapper);

        if (attention == null) {
            // 生成关注编号(4位随机数+10位时间戳)
            String time = Long.toString(System.currentTimeMillis());
            time = time.substring(0, time.length() - 3);
            String attentionNo = RandomUtils.getrandom(4) + time;
            patAttention.setAttentionNo(attentionNo);
            // 医生编号
            patAttention.setUserNo(myAttention.getDoctorNo());
            // 关注时间
            patAttention.setCreateTime(new Date());
            // 关注状态
            patAttention.setStatus("1");
            patAttentionService.save(patAttention);
            return ResponseResult.ok("关注成功!");
        } else if ("0".equals(attention.getStatus())) {
            // 已新增 直接修改
            attention.setStatus("1");
            patAttentionService.updateById(attention);
            return ResponseResult.ok("关注成功!");
        } else if ("1".equals(attention.getStatus())) {
            return ResponseResult.ok("该医生已关注，不可再次点击关注!");
        }
        return ResponseResult.error("关注失败!");
    }

    @ApiOperation(value = "取消关注")
    @RequestMapping(value = "/deletePatAttention", method = RequestMethod.POST)
    public ResponseResult deletePatAttention(@RequestBody GetMyAttention myAttention) {
        try {
            PatAttention patAttention = new PatAttention();
            // 获取patNo
            String patNo = (String) StpUtil.getLoginIdByToken((String) ThreadLocalUtils.get("Authorization"));
            log.info("用户号:" + patNo);
            // 关注状态
            patAttention.setStatus("0");
            UpdateWrapper<PatAttention> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("pat_no", patNo);
            updateWrapper.eq("user_no", myAttention.getDoctorNo());
            boolean isFlag = patAttentionService.update(patAttention, updateWrapper);
            if (!isFlag) {
                return ResponseResult.error("修改出错，请检查代码。");
            }
            return ResponseResult.ok("取消关注成功!");
        } catch (NullPointerException e) {
            return ResponseResult.error("请求头参数Authorization为空!");
        }
    }


}
