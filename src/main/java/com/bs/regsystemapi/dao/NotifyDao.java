package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Notify;
import com.bs.regsystemapi.modal.dto.notify.QueryNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.QueryUserNotifyForm;
import com.bs.regsystemapi.modal.vo.notify.NotSend;
import com.bs.regsystemapi.modal.vo.notify.NotifyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:24
 */
@Mapper
public interface NotifyDao extends BaseMapper<Notify> {
    List<NotifyInfo> getNotifyList(QueryNotifyForm form);
    List<NotSend> getNotSendList(String notifyNo);
    Integer deleteNotify(String notifyNo);
    Integer deleteNotifyByUser(String systemNo);
    List<Notify> getNotifyListForUser(QueryUserNotifyForm form);
    Integer getCountNotRead(String notifyObject);
    Integer toRead(String systemNo);
    Integer batchToRead(String notifyObject);
    Notify getNotifyInfo(String systemNo);
    Notify adminGetNotifyInfo(String notifyNo);
    List<String> getNotifyObjectList(String notifyNo);
}
