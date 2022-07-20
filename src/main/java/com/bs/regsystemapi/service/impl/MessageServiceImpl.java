package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.MessageDao;
import com.bs.regsystemapi.entity.Actor;
import com.bs.regsystemapi.entity.Messages;
import com.bs.regsystemapi.modal.dto.file.UpdateFilePathForm;
import com.bs.regsystemapi.modal.dto.message.MessageFrom;
import com.bs.regsystemapi.modal.dto.message.UpdateFileUploadTimeForm;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;
import com.bs.regsystemapi.modal.vo.MessageInfo;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import com.bs.regsystemapi.service.MessageService;
import com.bs.regsystemapi.service.PatientService;
import com.bs.regsystemapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/1 14:51
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, Messages> implements MessageService {

    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    @Override
    public List<MessageInfo<UserInfo, UserInfo>> getMessage(MessageFrom from) {
        return this.baseMapper.getMessage(from);
    }

    @Override
    public List<MessageInfo<UserInfo, PatientInfo>> getMessageDoctorToPatient(MessageFrom from) {
        return this.baseMapper.getMessageDoctorToPatient(from);
    }

    @Override
    public List<MessageInfo<PatientInfo, UserInfo>> getMessagePatientToDoctor(MessageFrom from) {
        return this.baseMapper.getMessagePatientToDoctor(from);
    }

    @Override
    public List<MessageInfo<UserInfo, UserInfo>> getMessageByNo(String receiverNo) {
        return this.baseMapper.getMessageByNo(receiverNo);
    }

    @Override
    public List<Object> getMessageByActorNo(String actorNo) {
        List<Object> resultObj = new ArrayList<>();
        List<MessageInfo<UserInfo, PatientInfo>> messageByActorNo = this.baseMapper.getMessageByActorNo(actorNo);
        List<MessageInfo<PatientInfo, UserInfo>> messageByReceiverNo = this.baseMapper.getMessageByReceiverNo(actorNo);

        // 遍历当前患者发送的消息
        for (MessageInfo<UserInfo, PatientInfo> actorInfo : messageByActorNo) {
            // 遍历当前患者接受的消息
            MessageInfo<PatientInfo, UserInfo> info = new MessageInfo<>();
            String doctorNo;
            // 判断返回结果集是否增加了消息对象
            boolean flag = true;
            for (MessageInfo<PatientInfo, UserInfo> receiverInfo : messageByReceiverNo) {
                // 分组：接受者No等于发送者No，并且发送者No等于接收者No，则是一组
                if (receiverInfo.getActorNo().equals(actorInfo.getReceiverNo())
                        && receiverInfo.getReceiverNo().equals(actorInfo.getActorNo())
                ) {
                    // 患者作为发送者，是最后一次发消息的人
                    if (actorInfo.getSendTime().getTime() > receiverInfo.getSendTime().getTime()) {
                        // 查询医生的详细信息即可
                        // 医生No
                        doctorNo = actorInfo.getReceiverNo();
                        UserInfo userInfo = userService.getBaseUserInfo(doctorNo);
                        BeanUtils.copyProperties(actorInfo, info);
                        // 构建需要的结果集：将接受者固定存放医生
                        info.setReceiver(new Actor<>(userInfo));
                    } else {
                        // 患者作为接受者，医生才是最后一次发送消息的人
                        doctorNo = receiverInfo.getActorNo();
                        UserInfo userInfo = userService.getBaseUserInfo(doctorNo);
                        BeanUtils.copyProperties(receiverInfo, info);
                        // 构建需要的结果集：将接受者固定存放医生
                        info.setReceiver(new Actor<>(userInfo));
                    }
                    flag = false;
                }
            }
            // 没增加消息，则直接插入消息
            if (flag) {
                // 没找到消息组，则说明患者给医生发送了消息，发送者是患者，医生没有回复
                doctorNo = actorInfo.getReceiverNo();
                UserInfo userInfo = userService.getBaseUserInfo(doctorNo);
                BeanUtils.copyProperties(actorInfo, info);
                // 构建需要的结果集：将接受者固定存放医生
                info.setReceiver(new Actor<>(userInfo));
            }
            resultObj.add(info);
        }
        return resultObj;
    }

    @Override
    public int getNewsCount(String receiverNo) {
        return this.baseMapper.getNewsCount(receiverNo);
    }

    @Override
    public int updateReadStatus(UpdateReadStatusForm form) {
        return this.baseMapper.updateReadStatus(form);
    }

    @Override
    public int updateFilePathForm(UpdateFilePathForm form) {
        return this.baseMapper.updateFilePathForm(form);
    }

    @Override
    public int updateFileUploadTime(UpdateFileUploadTimeForm form) {
        return this.baseMapper.updateFileUploadTime(form);
    }
}
