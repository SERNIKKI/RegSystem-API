package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.NotifyDao;
import com.bs.regsystemapi.entity.Notify;
import com.bs.regsystemapi.modal.dto.notify.QueryNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.QueryUserNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.SendNotifyForm;
import com.bs.regsystemapi.modal.vo.notify.NotSend;
import com.bs.regsystemapi.modal.vo.notify.NotifyInfo;
import com.bs.regsystemapi.service.NotifyService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:26
 */
@Service("notifyService")
public class NotifyServiceImpl extends ServiceImpl<NotifyDao, Notify> implements NotifyService {
    @Override
    public ManagePageResult getNotifyList(QueryNotifyForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<NotifyInfo> notifyList = this.baseMapper.getNotifyList(form);
        PageInfo<NotifyInfo> notifyInfoPageInfo = new PageInfo<>(notifyList);
        List<NotifyInfo> list = notifyInfoPageInfo.getList();
        for(NotifyInfo notifyInfo : list) {
            List<NotSend> notSendList = this.baseMapper.getNotSendList(notifyInfo.getNotifyNo());
            notifyInfo.setNotSends(notSendList);
        }
        return ManagePageResult.getPageResult(notifyInfoPageInfo);
    }

    @Override
    public List<NotSend> getNotSendList(String notifyNo) {
        List<NotSend> notSendList = this.baseMapper.getNotSendList(notifyNo);
        return notSendList;
    }

    @Override
    public Integer deleteNotify(String notifyNo) {
        Integer integer = this.baseMapper.deleteNotify(notifyNo);
        return integer;
    }

    @Override
    public Integer deleteNotifyByUser(String systemNo) {
        Integer integer = this.baseMapper.deleteNotifyByUser(systemNo);
        return integer;
    }

    @Override
    public ManagePageResult getNotifyListForUser(QueryUserNotifyForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Notify> notifyListForUser = this.baseMapper.getNotifyListForUser(form);
        PageInfo<Notify> notifyPageInfo = new PageInfo<>(notifyListForUser);
        return ManagePageResult.getPageResult(notifyPageInfo);
    }

    @Override
    public Integer getCountNotRead(String notifyObject) {
        Integer countNotRead = this.baseMapper.getCountNotRead(notifyObject);
        return countNotRead;
    }

    @Override
    public void toRead(String systemNo) {
        this.baseMapper.toRead(systemNo);
    }

    @Override
    public void batchToRead(String notifyObject) {
        this.baseMapper.batchToRead(notifyObject);
    }

    @Override
    public Notify getNotifyInfo(String systemNo) {
        Notify notifyInfo = this.baseMapper.getNotifyInfo(systemNo);
        return notifyInfo;
    }

    @Override
    public SendNotifyForm adminGetNotifyInfo(String notifyNo) {
        Notify notify = this.baseMapper.adminGetNotifyInfo(notifyNo);
        notify.setNotifyObject("");
        List<String> notifyObjectList = this.baseMapper.getNotifyObjectList(notifyNo);
        SendNotifyForm form = new SendNotifyForm();
        form.setNotify(notify);
        form.setNotifyObjects(notifyObjectList);
        return form;
    }
}
