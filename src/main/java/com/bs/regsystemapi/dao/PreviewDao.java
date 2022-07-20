package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Preview;
import com.bs.regsystemapi.modal.dto.preview.QueryPreviewForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:27
 */
@Mapper
public interface PreviewDao extends BaseMapper<Preview> {
    List<Preview> getPreviewList(QueryPreviewForm form);
    List<Preview> getPCPreview();
    List<Preview> getAPPPreview();
    Preview getPreviewInfo(String previewNo);
    Integer deletePreview(String previewNo);
    Integer recoverPreview(String previewNo);
}
