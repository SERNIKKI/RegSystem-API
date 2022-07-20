package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Bulletin;
import com.bs.regsystemapi.modal.dto.bulletin.QueryBulletinForm;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:22
 */
public interface BulletinService extends IService<Bulletin> {
    ManagePageResult getBulletinList(QueryBulletinForm form);
    List<Bulletin> getValidBulletin();
    Bulletin getBulletinInfo(String bulletinNo);
    Integer deleteBulletin(String bulletinNo);
    Integer recoverBulletin(String bulletinNo);
}
