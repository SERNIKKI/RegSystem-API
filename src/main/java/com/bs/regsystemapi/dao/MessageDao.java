package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Messages;
import com.bs.regsystemapi.modal.dto.file.UpdateFilePathForm;
import com.bs.regsystemapi.modal.dto.message.MessageFrom;
import com.bs.regsystemapi.modal.dto.message.UpdateFileUploadTimeForm;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;
import com.bs.regsystemapi.modal.vo.MessageInfo;
import com.bs.regsystemapi.modal.vo.UserInfo;
import com.bs.regsystemapi.modal.vo.patient.PatientInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/1 14:49
 */
@Mapper
public interface MessageDao extends BaseMapper<Messages> {
    List<MessageInfo<UserInfo, UserInfo>> getMessage(MessageFrom from);

    List<MessageInfo<UserInfo, PatientInfo>> getMessageDoctorToPatient(MessageFrom from);

    List<MessageInfo<PatientInfo, UserInfo>> getMessagePatientToDoctor(MessageFrom from);
    List<MessageInfo<UserInfo, UserInfo>> getMessageByNo(String receiverNo);
    int getNewsCount(String receiverNo);
    int updateReadStatus(UpdateReadStatusForm form);
    int updateFilePathForm(UpdateFilePathForm form);
    int updateFileUploadTime(UpdateFileUploadTimeForm form);

    List<MessageInfo<UserInfo, PatientInfo>> getMessageByActorNo(String actorNo);
    List<MessageInfo<PatientInfo, UserInfo>> getMessageByReceiverNo(String receiverNo);
}
