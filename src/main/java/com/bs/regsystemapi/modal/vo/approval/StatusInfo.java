package com.bs.regsystemapi.modal.vo.approval;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/30 17:24
 */
@Data
public class StatusInfo implements Serializable {

    private String total;

    private String revoke;

    private String unprocessed;

    private String processed;

    private String reject;

}
