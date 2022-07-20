package com.bs.regsystemapi.controller;

import com.bs.regsystemapi.entity.Actor;
import com.bs.regsystemapi.entity.MessageCount;
import com.bs.regsystemapi.entity.Messages;
import com.bs.regsystemapi.modal.dto.message.MessageFrom;
import com.bs.regsystemapi.modal.dto.file.UpdateFilePathForm;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;
import com.bs.regsystemapi.modal.vo.MessageInfo;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.service.*;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/2 15:00
 */

@RestController
@RequestMapping("/message")
@Api(value = "消息记录api接口")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageCountService messageCountService;
    @Autowired
    private LogService logService;
    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "获取消息列表")
    @PostMapping(value = "/list")
    public ResponseResult getMessage(@RequestBody MessageFrom from) {
        List<MessageInfo<UserInfo, UserInfo>> message = messageService.getMessage(from);
        for(MessageInfo<UserInfo, UserInfo> messageInfo : message) {
            String actorNo = messageInfo.getActorNo();
            UserInfo actor = userService.getBaseUserInfo(actorNo);
            // 没有数据则说明是患者
            if(actor == null) {
                actor = new UserInfo();
                PatientInfo basePatientInfo = patientService.getBasePatientInfo(messageInfo.getActorNo());
                actor.setUserAvatar(basePatientInfo.getPatImgsrc());
                actor.setUserName(basePatientInfo.getPatRealName());
                actor.setUserType("pat");
            }
            Actor<UserInfo> infoActor = new Actor<>(actor);
            messageInfo.setActor(infoActor);
            UserInfo receiver = userService.getBaseUserInfo(messageInfo.getReceiverNo());
            messageInfo.setReceiver(new Actor<>(receiver));
        }
        return ResponseResult.ok().put(message);
    }

    @ApiOperation(value = "获取消息首页的消息列表")
    @GetMapping(value = "/notifyList/{receiverNo}")
    public ResponseResult getMessageByNo(@PathVariable("receiverNo") String receiverNo) {
        if (StringUtils.isEmpty(receiverNo)) {
            return ResponseResult.error("接收人no为空");
        }
        List<MessageInfo<UserInfo, UserInfo>> messageByNo = messageService.getMessageByNo(receiverNo);
        List<MessageInfo<UserInfo, UserInfo>> removeList = new ArrayList<>();
        for (MessageInfo<UserInfo, UserInfo> messageInfo : messageByNo) {
            // 判断接收人是否为接收者
            if(!messageInfo.getReceiverNo().equals(receiverNo)) {
                String actorNo = messageInfo.getReceiverNo();
                messageInfo.setActorNo(actorNo);
                messageInfo.setReceiverNo(receiverNo);
            }
            UserInfo receiver = userService.getBaseUserInfo(messageInfo.getReceiverNo());
            messageInfo.setReceiver(new Actor<>(receiver));
            // 医生获取
            UserInfo actor = userService.getBaseUserInfo(messageInfo.getActorNo());
            // 没有数据则说明是患者
            if(actor == null) {
                actor = new UserInfo();
                PatientInfo basePatientInfo = patientService.getBasePatientInfo(messageInfo.getActorNo());
                if(basePatientInfo == null) {
                    // 说明该用户被物理删除
                    removeList.add(messageInfo);
                } else {
                    actor.setUserAvatar(basePatientInfo.getPatImgsrc() == null ? "" : basePatientInfo.getPatImgsrc());
                    actor.setUserName(basePatientInfo.getPatRealName());
                    actor.setUserType("pat");
                }
            }
            messageInfo.setActor(new Actor<>(actor));
            // 设置未读消息数目
            Integer notRead = messageCountService.getNotRead(new UpdateReadStatusForm(messageInfo.getActorNo(), messageInfo.getReceiverNo()));
            if(notRead == null) {
                notRead = 0;
            }
            messageInfo.setNotRead(notRead);
        }
        if(removeList.size() > 0) {
            messageByNo.removeAll(removeList);
        }
        return ResponseResult.ok().put(messageByNo);
    }

    @ApiOperation(value = "获取未读消息条数")
    @GetMapping(value = "/getNewsCount/{receiverNo}")
    public ResponseResult getNewsCount(@PathVariable("receiverNo") String receiverNo) {
        if(StringUtils.isEmpty(receiverNo)) {
            return ResponseResult.error("接收人no为空");
        }
        int newsCount = messageCountService.getNotReadCount(receiverNo);
        return ResponseResult.ok().put(newsCount);
    }

    @ApiOperation(value = "将未读消息全部设为已读")
    @PostMapping(value = "/updateReadStatus")
    public ResponseResult updateReadStatus(@RequestBody UpdateReadStatusForm form) {
        int i = messageService.updateReadStatus(form);
        messageCountService.clearNotRead(form);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "发送消息")
    @PostMapping(value = "/sendMessage")
    @Transactional
    public ResponseResult sendMessage(@RequestBody Messages messages) {
        messages.setSendTime(new Date());
        messages.setSendStatus(1L);
        String messageNo = NoGeneratorUtil.getNo();
        messages.setMessageNo(messageNo);
        boolean save = messageService.save(messages);
        MessageCount messageCount = new MessageCount();
        messageCount.setActorNo(messages.getActorNo());
        messageCount.setReceiverNo(messages.getReceiverNo());
        messageCount.setLastTime(messages.getSendTime());
        messageCount.setIsSuccess(1L);
        messageCount.setIsError(0L);
        messageCount.setRemark("");
        messageCountService.updateMessageCount(messageCount);
        return ResponseResult.ok().put(messageNo);
    }

    @ApiOperation(value = "填入文件url与发送状态")
    @PostMapping(value = "/updateFilePath")
    public ResponseResult updateFilePathForm(@RequestBody UpdateFilePathForm form) {
        int i = messageService.updateFilePathForm(form);
        return ResponseResult.ok().put(i);
    }
}
