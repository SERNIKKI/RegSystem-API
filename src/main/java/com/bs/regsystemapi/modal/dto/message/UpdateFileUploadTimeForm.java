package com.bs.regsystemapi.modal.dto.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/13 23:07
 */
@Data
public class UpdateFileUploadTimeForm implements Serializable {

    private Date fileUploadTime;

    private String messageNo;
}
