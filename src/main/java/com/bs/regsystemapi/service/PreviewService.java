package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Preview;
import com.bs.regsystemapi.modal.dto.preview.QueryPreviewForm;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:28
 */
public interface PreviewService extends IService<Preview> {
    ManagePageResult getPreviewList(QueryPreviewForm form);
    List<Preview> getPCPreview();
    List<Preview> getAPPPreview();
    Preview getPreviewInfo(String previewNo);
    Integer deletePreview(String previewNo);
    Integer recoverPreview(String previewNo);
    void savePreview(Preview preview);
    void updatePreview(Preview preview);
}
