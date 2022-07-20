package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Notify;
import com.bs.regsystemapi.modal.dto.notify.QueryNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.QueryUserNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.SendNotifyForm;
import com.bs.regsystemapi.modal.vo.notify.NotSend;
import com.bs.regsystemapi.modal.vo.notify.NotifyInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:25
 */
public interface NotifyService extends IService<Notify> {
    ManagePageResult getNotifyList(QueryNotifyForm form);
    List<NotSend> getNotSendList(String notifyNo);
    Integer deleteNotify(String notifyNo);
    Integer deleteNotifyByUser(String systemNo);
    ManagePageResult getNotifyListForUser(QueryUserNotifyForm form);
    Integer getCountNotRead(String notifyObject);
    void toRead(String systemNo);
    void batchToRead(String notifyObject);
    Notify getNotifyInfo(String systemNo);
    SendNotifyForm adminGetNotifyInfo(String notifyNo);
}
