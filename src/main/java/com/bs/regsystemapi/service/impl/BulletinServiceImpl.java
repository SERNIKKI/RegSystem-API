package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.BulletinDao;
import com.bs.regsystemapi.entity.Bulletin;
import com.bs.regsystemapi.modal.dto.bulletin.QueryBulletinForm;
import com.bs.regsystemapi.service.BulletinService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:22
 */
@Service("bulletinService")
public class BulletinServiceImpl extends ServiceImpl<BulletinDao, Bulletin> implements BulletinService {
    @Override
    public ManagePageResult getBulletinList(QueryBulletinForm form) {
        PageHelper.startPage(form.getPageNum(),form.getPageSize());
        List<Bulletin> bulletinList = this.baseMapper.getBulletinList(form);
        PageInfo<Bulletin> bulletinPageInfo = new PageInfo<>(bulletinList);
        return ManagePageResult.getPageResult(bulletinPageInfo);
    }

    @Override
    public List<Bulletin> getValidBulletin() {
        return this.baseMapper.getValidBulletin();
    }

    @Override
    public Bulletin getBulletinInfo(String bulletinNo) {
        Bulletin bulletinInfo = this.baseMapper.getBulletinInfo(bulletinNo);
        return bulletinInfo;
    }

    @Override
    public Integer deleteBulletin(String bulletinNo) {
        Integer integer = this.baseMapper.deleteBulletin(bulletinNo);
        return integer;
    }

    @Override
    public Integer recoverBulletin(String bulletinNo) {
        Integer integer = this.baseMapper.recoverBulletin(bulletinNo);
        return integer;
    }
}
