package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.entity.Actor;
import com.bs.regsystemapi.modal.dto.message.MessageFrom;
import com.bs.regsystemapi.modal.vo.MessageInfo;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.service.MessageCountService;
import com.bs.regsystemapi.service.MessageService;
import com.bs.regsystemapi.service.PatientService;
import com.bs.regsystemapi.service.UserService;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patMessage")
@Api(value = "患者消息记录api接口")
public class PatientMessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private MessageCountService messageCountService;

    @ApiOperation(value = "获取消息列表(医生对患者)")
    @PostMapping(value = "/listOfDoctorToPatient")
    public ResponseResult getMessageListOfDoctorToPatient(@RequestBody MessageFrom from) {
        List<MessageInfo<UserInfo, PatientInfo>> message = messageService.getMessageDoctorToPatient(from);
        for(MessageInfo<UserInfo, PatientInfo> messageInfo : message) {
            String actorNo = messageInfo.getActorNo();
            // 获取发送者个人信息
            UserInfo actor = userService.getBaseUserInfo(actorNo);
            Actor<UserInfo> infoActor = new Actor<>(actor);
            messageInfo.setActor(infoActor);
            // 获取接受者基本信息
            PatientInfo receiver = patientService.getBasePatientInfo(messageInfo.getReceiverNo());
            messageInfo.setReceiver(new Actor<>(receiver));
        }
        return ResponseResult.ok().put(message);
    }

    @ApiOperation(value = "获取消息列表(患者对医生)")
    @PostMapping(value = "/listOfPatientToDoctor")
    public ResponseResult getMessageListOfPatientToDoctor(@RequestBody MessageFrom from) {
        List<MessageInfo<PatientInfo, UserInfo>> message = messageService.getMessagePatientToDoctor(from);
        for(MessageInfo<PatientInfo, UserInfo> messageInfo : message) {
            // 获取发送者个人信息
            PatientInfo receiver = patientService.getBasePatientInfo(messageInfo.getActorNo());
            messageInfo.setActor(new Actor<>(receiver));
            // 获取接受者基本信息
            UserInfo actor = userService.getBaseUserInfo(messageInfo.getReceiverNo());
            messageInfo.setReceiver(new Actor<>(actor));
        }
        return ResponseResult.ok().put(message);
    }


    @ApiOperation(value = "获取消息首页的消息列表")
    @GetMapping(value = "/notifyList/{actorNo}")
    public ResponseResult getMessageByNo(@PathVariable("actorNo") String actorNo) {
        if (StringUtils.isEmpty(actorNo)) {
            return ResponseResult.error("接收人no为空");
        }
        // 通过发送者No查询消息
        List<Object> messageByNo = messageService.getMessageByActorNo(actorNo);

        return ResponseResult.ok().put(messageByNo);
    }

}
