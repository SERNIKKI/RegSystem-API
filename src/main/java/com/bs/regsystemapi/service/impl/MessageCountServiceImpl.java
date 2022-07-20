package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.MessageCountDao;
import com.bs.regsystemapi.entity.MessageCount;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;
import com.bs.regsystemapi.service.MessageCountService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

/**
 * @author qpj
 * @date 2022/3/4 14:53
 */
@Service("messageCountService")
public class MessageCountServiceImpl extends ServiceImpl<MessageCountDao, MessageCount> implements MessageCountService {

    @Override
    public Integer getNotRead(UpdateReadStatusForm form) {
        return this.baseMapper.getNotRead(form);
    }

    @Override
    public void clearNotRead(UpdateReadStatusForm form) {
        this.baseMapper.clearNotRead(form);
    }

    @Override
    public int getNotReadCount(String receiverNo) {
        Integer notReadCount = this.baseMapper.getNotReadCount(receiverNo);
        if(notReadCount == null) {
            return 0;
        } else {
            return notReadCount;
        }
    }

    @Override
    public void updateMessageCount(MessageCount messageCount) {
        this.baseMapper.updateMessageCount(messageCount);
    }
}
