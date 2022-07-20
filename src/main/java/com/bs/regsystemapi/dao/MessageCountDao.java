package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.MessageCount;
import com.bs.regsystemapi.modal.dto.message.UpdateReadStatusForm;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qpj
 * @date 2022/3/4 14:47
 */
@Mapper
public interface MessageCountDao extends BaseMapper<MessageCount> {
    Integer getNotRead(UpdateReadStatusForm form);
    void clearNotRead(UpdateReadStatusForm form);
    Integer getNotReadCount(String receiverNo);
    void updateMessageCount(MessageCount messageCount);
}
