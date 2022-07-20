package com.bs.regsystemapi.modal.dto.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/13 1:43
 */
@Data
public class UpdateFilePathForm implements Serializable {

    private String fileUrl;

    private String fileStatus;

    private String messageNo;
}
