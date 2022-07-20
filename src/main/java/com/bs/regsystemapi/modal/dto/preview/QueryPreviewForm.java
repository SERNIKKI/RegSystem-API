package com.bs.regsystemapi.modal.dto.preview;

import com.bs.regsystemapi.utils.ManagePageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/30 15:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPreviewForm extends ManagePageResult implements Serializable {

    private String previewTitle;

    private String showModel;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String createName;
}
