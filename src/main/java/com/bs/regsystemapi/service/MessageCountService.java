package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.MessageCount;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;

/**
 * @author qpj
 * @date 2022/3/4 14:54
 */
public interface MessageCountService extends IService<MessageCount> {
    Integer getNotRead(UpdateReadStatusForm form);
    void clearNotRead(UpdateReadStatusForm form);
    int getNotReadCount(String receiverNo);
    void updateMessageCount(MessageCount messageCount);
}
