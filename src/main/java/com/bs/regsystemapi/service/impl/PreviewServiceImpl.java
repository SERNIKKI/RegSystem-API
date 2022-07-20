package com.bs.regsystemapi.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PreviewDao;
import com.bs.regsystemapi.entity.Preview;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.preview.QueryPreviewForm;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.PreviewService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:28
 */
@Service("previewService")
public class PreviewServiceImpl extends ServiceImpl<PreviewDao, Preview> implements PreviewService {

    @Autowired
    private LogService logService;
    @Autowired
    private PreviewService previewService;

    @Override
    public ManagePageResult getPreviewList(QueryPreviewForm form) {
        PageHelper.startPage(form.getPageNum(),form.getPageSize());
        List<Preview> previewList = this.baseMapper.getPreviewList(form);
        PageInfo<Preview> previewPageInfo = new PageInfo<>(previewList);
        return ManagePageResult.getPageResult(previewPageInfo);
    }

    @Override
    public List<Preview> getPCPreview() {
        return this.baseMapper.getPCPreview();
    }

    @Override
    public List<Preview> getAPPPreview() {
        return this.baseMapper.getAPPPreview();
    }

    @Override
    public Preview getPreviewInfo(String previewNo) {
        return this.baseMapper.getPreviewInfo(previewNo);
    }

    @Override
    public Integer deletePreview(String previewNo) {
        Integer integer = this.baseMapper.deletePreview(previewNo);
        if(integer > 0) {
            logService.addLog(Action.DELETE, Table.PREVIEW, previewNo);
        } else {
            logService.addLog(Action.DELETE, Table.PREVIEW, previewNo, Status.FAIL);
        }
        return integer;
    }

    @Override
    public Integer recoverPreview(String previewNo) {
        Integer integer = this.baseMapper.recoverPreview(previewNo);
        if(integer > 0) {
            logService.addLog(Action.RECOVER, Table.PREVIEW, previewNo);
        } else {
            logService.addLog(Action.RECOVER, Table.PREVIEW, previewNo, Status.FAIL);
        }
        return integer;
    }

    @Override
    public void savePreview(Preview preview) {
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        preview.setPreviewNo(NoGeneratorUtil.getNo());
        preview.setCreateName(user.getUserRealName());
        preview.setCreateTime(new Date());
        preview.setUpdateName(user.getUserRealName());
        preview.setUpdateTime(new Date());
        boolean save = previewService.save(preview);
        if(save) {
            logService.addLog(Action.INSERT, Table.PREVIEW, preview.getPreviewNo());
        } else {
            logService.addLog(Action.INSERT, Table.PREVIEW, preview.getPreviewNo(), Status.FAIL);
        }
    }

    @Override
    public void updatePreview(Preview preview) {
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        preview.setUpdateName(user.getUserRealName());
        preview.setUpdateTime(new Date());
        boolean b = previewService.updateById(preview);
        if(b) {
            logService.addLog(Action.UPDATE, Table.PREVIEW, preview.getPreviewNo());
        } else {
            logService.addLog(Action.UPDATE, Table.PREVIEW, preview.getPreviewNo(), Status.FAIL);
        }
    }


}
